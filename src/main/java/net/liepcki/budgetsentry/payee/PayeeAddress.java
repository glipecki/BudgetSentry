package net.liepcki.budgetsentry.payee;

import lombok.Builder;
import lombok.Data;

/**
 * @author gregorry
 */
@Data
@Builder
public class PayeeAddress {

    private String street;
    private String city;
    private String postalCode;

}
