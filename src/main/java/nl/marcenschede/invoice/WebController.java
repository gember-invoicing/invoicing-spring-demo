package nl.marcenschede.invoice;

import nl.marcenschede.invoice.core.*;
import nl.marcenschede.invoice.core.functional.InvoiceData;
import nl.marcenschede.invoice.creators.impl.CompanyImpl;
import nl.marcenschede.invoice.creators.impl.InvoiceDataImpl;
import nl.marcenschede.invoice.creators.SampleCompanyCreator;
import nl.marcenschede.invoice.creators.SampleInvoiceDataCreator;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebController {
    private Company company;

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
    public InvoiceData postInvoiceData(@RequestBody InvoiceDataImpl invoiceData) {

        System.out.println(invoiceData.toString());

//        Function<Company, Function<InvoiceData, InvoiceTotals>> invoiceCalculatorFactory =
//                InvoiceCalculatorFactory.getInvoiceCalculatorFactory(new VatRepositoryImpl());
//
//        final Function<InvoiceData, InvoiceTotals> invoiceFunction = invoiceCalculatorFactory.apply(company);
//        return invoiceFunction.apply(invoiceData);


        return invoiceData;
    }

}
