package nl.marcenschede.invoice.creators.impl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nl.marcenschede.invoice.core.Customer;
import nl.marcenschede.invoice.core.InvoiceLine;
import nl.marcenschede.invoice.core.InvoiceType;
import nl.marcenschede.invoice.core.ProductCategory;
import nl.marcenschede.invoice.core.functional.InvoiceData;

import java.util.List;
import java.util.Optional;

public class InvoiceDataImpl implements InvoiceData {
    private String countryOfOrigin = null;
    private String countryOfDestination = null;
    private Customer customer;
    private InvoiceType invoiceType;
    private ProductCategory productCategory;
    private boolean vatShifted;
    private List<InvoiceLine> invoiceLines;

    public InvoiceDataImpl() {
    }

    @Override
    public Optional<String> getCountryOfOrigin() {
        return Optional.ofNullable(countryOfOrigin);
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @Override
    public Optional<String> getCountryOfDestination() {
        return Optional.ofNullable(countryOfDestination);
    }

    public void setCountryOfDestination(String countryOfDestination) {
        this.countryOfDestination = countryOfDestination;
    }

    @Override
    @JsonDeserialize(as = CustomerImpl.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Override
    public Optional<ProductCategory> getProductCategory() {
        return Optional.ofNullable(productCategory);
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public Boolean getVatShifted() {
        return vatShifted;
    }

    public void setVatShifted(boolean vatShifted) {
        this.vatShifted = vatShifted;
    }

    @Override
    @JsonDeserialize(as = List.class, contentAs = InvoiceLineImpl.class)
    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }

    @Override
    public String toString() {
        return "InvoiceDataImpl{" +
                "countryOfOrigin='" + countryOfOrigin + '\'' +
                ", countryOfDestination='" + countryOfDestination + '\'' +
                ", customer=" + customer +
                ", invoiceType=" + invoiceType +
                ", productCategory=" + productCategory +
                ", vatShifted=" + vatShifted +
                ", invoiceLines=" + invoiceLines +
                '}';
    }
}
