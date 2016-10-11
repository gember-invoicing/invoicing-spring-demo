package nl.marcenschede.invoice.creators;

import nl.marcenschede.invoice.core.Company;
import nl.marcenschede.invoice.core.VatCalculationPolicy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SampleCompanyCreator {
    public Company invoke() {
        return new Company() {
            @Override
            public VatCalculationPolicy getVatCalculationPolicy() {
                return VatCalculationPolicy.VAT_CALCULATION_ON_TOTAL;
            }

            @Override
            public String getPrimaryCountryIso() {
                return "NL";
            }

            @Override
            public Map<String, String> getVatRegistrations() {
                Map<String, String> result = new HashMap<>();
                result.put("NL", "NL12345");
                return result;
            }

            @Override
            public boolean hasVatRegistrationFor(String s) {
                return false;
            }

            @Override
            public Optional<String> getVatRegistrationInCountry(String s) {
                return null;
            }
        };
    }
}
