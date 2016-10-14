package nl.marcenschede.invoice;

import nl.marcenschede.invoice.core.Company;
import nl.marcenschede.invoice.core.InvoiceTotals;
import nl.marcenschede.invoice.core.data.VatRepositoryImpl;
import nl.marcenschede.invoice.core.functional.InvoiceCalculatorFactory;
import nl.marcenschede.invoice.core.functional.InvoiceCreationEvent;
import nl.marcenschede.invoice.core.functional.InvoiceCreationFactory;
import nl.marcenschede.invoice.core.functional.InvoiceData;
import nl.marcenschede.invoice.creators.SampleCompanyCreator;
import nl.marcenschede.invoice.creators.SampleInvoiceDataCreator;
import nl.marcenschede.invoice.creators.impl.CompanyImpl;
import nl.marcenschede.invoice.creators.impl.InvoiceDataImpl;
import nl.marcenschede.invoice.eventProcessors.DebtorBookEventCreator;
import nl.marcenschede.invoice.eventProcessors.InvoiceCreatedEventProcessor;
import nl.marcenschede.invoice.eventProcessors.InvoicePdfStreamCreator;
import nl.marcenschede.invoice.eventProcessors.LedgerEventCreator;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
public class WebController {
    private Company company;
    private final Supplier<Long> invoiceNumberGenerator;

    public WebController() {
        invoiceNumberGenerator = new Supplier<Long>() {
            private Long nextInvoiceNumber = 201601000001L;

            @Override
            public Long get() {
                return nextInvoiceNumber++;
            }
        };
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    @ResponseBody
    public Company getCompanyTemplate() {

        Company company = new SampleCompanyCreator().invoke();

        return company;
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    @ResponseBody
    public Company postCompanyData(@RequestBody CompanyImpl company) {
        this.company = company;
        return company;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.GET)
    @ResponseBody
    public InvoiceDataImpl getInvoiceTemplate() {

        InvoiceDataImpl invoiceData = new SampleInvoiceDataCreator().invoke();

        return invoiceData;
    }

    @RequestMapping(value = "/invoice", method = RequestMethod.POST)
    @ResponseBody
    public InvoiceTotals postInvoiceData(@RequestBody InvoiceDataImpl invoiceData) {

        final Function<Company, Function<InvoiceData, InvoiceTotals>> invoiceCalculatorFactory =
                InvoiceCalculatorFactory.getInvoiceCalculatorFactory(new VatRepositoryImpl());
        final Function<InvoiceData, InvoiceTotals> invoiceFunction = invoiceCalculatorFactory.apply(company);

        return invoiceFunction.apply(invoiceData);
    }

    @RequestMapping(value = "/createinvoice", method = RequestMethod.POST)
    public HttpEntity<byte[]> createInvoice(@RequestBody InvoiceDataImpl invoiceData) throws IOException {

        final ByteArrayOutputStream pdfBuffer = new ByteArrayOutputStream();

        final Consumer<InvoiceData> invoiceCreator = getInvoiceCreator(pdfBuffer);
        invoiceCreator.accept(invoiceData);

        HttpHeaders header = getHttpHeaders(pdfBuffer.toByteArray(), 0L);
        return new HttpEntity<byte[]>(pdfBuffer.toByteArray(), header);
    }

    private Consumer<InvoiceData> getInvoiceCreator(ByteArrayOutputStream pdfBuffer) {
        final Function<Consumer<InvoiceCreationEvent>, Consumer<InvoiceData>> invoiceCreationFactory = getInvoiceCalculator();
        final Consumer<InvoiceCreationEvent> eventConsumer = getEventConsumer(pdfBuffer);
        return invoiceCreationFactory.apply(eventConsumer);
    }

    private Consumer<InvoiceCreationEvent> getEventConsumer(ByteArrayOutputStream pdfBuffer) {
        final Consumer<InvoiceCreationEvent> pdfCreator = InvoicePdfStreamCreator.create(pdfBuffer);
        return new InvoiceCreatedEventProcessor(new LedgerEventCreator(), new DebtorBookEventCreator(), pdfCreator);
    }

    private Function<Consumer<InvoiceCreationEvent>, Consumer<InvoiceData>> getInvoiceCalculator() {
        return InvoiceCreationFactory.invoiceCreationFactory(invoiceNumberGenerator, company, new VatRepositoryImpl());
    }

    private HttpHeaders getHttpHeaders(byte[] bla, Long invoiceNumber) {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition", String.format("attachment; filename=\"invoice-%d.pdf\"", invoiceNumber));
        header.setContentLength(bla.length);
        return header;
    }
}
