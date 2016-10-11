package nl.marcenschede.invoice.creators.impl;

import nl.marcenschede.invoice.core.Customer;

import java.util.Optional;

public class CustomerImpl implements Customer {
    private String defaultCountry;
    private String euTaxId;
    private Long customerDebtorId;

    @Override
    public Optional<String> getDefaultCountry() {
        return Optional.ofNullable(defaultCountry);
    }

    public void setDefaultCountry(String defaultCountry) {
        this.defaultCountry = defaultCountry;
    }

    @Override
    public Optional<String> getEuTaxId() {
        return Optional.ofNullable(euTaxId);
    }

    public void setEuTaxId(String euTaxId) {
        this.euTaxId = euTaxId;
    }

    @Override
    public Long getCustomerDebtorId() {
        return customerDebtorId;
    }

    public void setCustomerDebtorId(Long customerDebtorId) {
        this.customerDebtorId = customerDebtorId;
    }
}
