package nl.marcenschede.invoice.creators;

import nl.marcenschede.invoice.core.InvoiceLine;
import nl.marcenschede.invoice.core.InvoiceLineVatType;
import nl.marcenschede.invoice.core.InvoiceType;
import nl.marcenschede.invoice.core.functional.InvoiceData;
import nl.marcenschede.invoice.core.tariffs.VatTariff;
import nl.marcenschede.invoice.creators.impl.InvoiceDataImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SampleInvoiceDataCreator {
    public InvoiceDataImpl invoke() {
        InvoiceDataImpl invoiceData = new InvoiceDataImpl();

        invoiceData.setCountryOfOrigin("NL");
        invoiceData.setCountryOfDestination("NL");
        invoiceData.setCustomer(new SampleCustomerCreator().invoke());
        invoiceData.setInvoiceLines(getInvoiceLines());
        invoiceData.setVatShifted(false);
        invoiceData.setInvoiceType(InvoiceType.CONSUMER);
        invoiceData.setProductCategory(null);

        return invoiceData;
    }

    public List<InvoiceLine> getInvoiceLines() {
        List<InvoiceLine> invoiceLines = new SampleInvoiceLinesCreator().invoke();

        return invoiceLines;
    }

}
