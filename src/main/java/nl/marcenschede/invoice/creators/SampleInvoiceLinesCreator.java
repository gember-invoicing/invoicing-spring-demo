package nl.marcenschede.invoice.creators;

import nl.marcenschede.invoice.core.InvoiceLine;
import nl.marcenschede.invoice.core.InvoiceLineVatType;
import nl.marcenschede.invoice.core.tariffs.VatTariff;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SampleInvoiceLinesCreator {
    public List<InvoiceLine> invoke() {
        List<InvoiceLine> invoiceLines = new ArrayList<>();

        invoiceLines.add(new InvoiceLine() {
            @Override
            public BigDecimal getLineAmount() {
                return new BigDecimal("100.00");
            }

            @Override
            public InvoiceLineVatType getInvoiceLineVatType() {
                return InvoiceLineVatType.EXCLUDING_VAT;
            }

            @Override
            public LocalDate getVatReferenceDate() {
                return LocalDate.of(2016, 2, 1);
            }

            @Override
            public VatTariff getVatTariff() {
                return VatTariff.HIGH;
            }
        });
        return invoiceLines;
    }
}
