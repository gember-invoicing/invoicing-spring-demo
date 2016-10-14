package nl.marcenschede.invoice.creators.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.marcenschede.invoice.core.InvoiceLine;
import nl.marcenschede.invoice.core.InvoiceLineVatType;
import nl.marcenschede.invoice.core.tariffs.VatTariff;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceLineImpl implements InvoiceLine {

    private BigDecimal lineAmount;
    private InvoiceLineVatType invoiceLineVatType;

    private LocalDate vatReferenceDate;

    private VatTariff vatTariff;

    @Override
    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }

    @Override
    public InvoiceLineVatType getInvoiceLineVatType() {
        return invoiceLineVatType;
    }

    public void setInvoiceLineVatType(InvoiceLineVatType invoiceLineVatType) {
        this.invoiceLineVatType = invoiceLineVatType;
    }

    @Override
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate getVatReferenceDate() {
        return vatReferenceDate;
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    public void setVatReferenceDate(LocalDate vatReferenceDate) {
        this.vatReferenceDate = vatReferenceDate;
    }

    @Override
    public VatTariff getVatTariff() {
        return vatTariff;
    }

    public void setVatTariff(VatTariff vatTariff) {
        this.vatTariff = vatTariff;
    }

    @Override
    public String toString() {
        return "InvoiceLineImpl{" +
                "lineAmount=" + lineAmount +
                ", invoiceLineVatType=" + invoiceLineVatType +
                ", vatReferenceDate=" + vatReferenceDate +
                ", vatTariff=" + vatTariff +
                '}';
    }
}
