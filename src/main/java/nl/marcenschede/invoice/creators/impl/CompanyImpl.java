package nl.marcenschede.invoice.creators.impl;

import nl.marcenschede.invoice.core.Company;
import nl.marcenschede.invoice.core.VatCalculationPolicy;

import java.util.Map;
import java.util.Optional;

/**
 * Created by marc on 11/10/2016.
 */
public class CompanyImpl implements Company {
    private VatCalculationPolicy vatCalculationPolicy;
    private String primaryCountryIso;
    private Map<String, String> vatRegistrations;

    @Override
    public VatCalculationPolicy getVatCalculationPolicy() {
        return vatCalculationPolicy;
    }

    @Override
    public String getPrimaryCountryIso() {
        return primaryCountryIso;
    }

    @Override
    public Map<String, String> getVatRegistrations() {
        return vatRegistrations;
    }

    @Override
    public boolean hasVatRegistrationFor(String s) {
        return vatRegistrations.containsKey(s);
    }

    @Override
    public Optional<String> getVatRegistrationInCountry(String country) {
        return Optional.ofNullable(vatRegistrations.get(country));
    }
}
