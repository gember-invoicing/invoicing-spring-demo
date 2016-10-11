package nl.marcenschede.invoice.creators;

import nl.marcenschede.invoice.core.Customer;

import java.util.Optional;

public class SampleCustomerCreator {
    public Customer invoke() {
        return new Customer() {
            @Override
            public Optional<String> getDefaultCountry() {
                return Optional.of("NL");
            }

            @Override
            public Optional<String> getEuTaxId() {
                return Optional.empty();
            }

            @Override
            public Long getCustomerDebtorId() {
                return 12345L;
            }
        };
    }
}

