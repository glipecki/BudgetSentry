package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;

/**
 * @author gregorry
 */
@Data
@Builder
public class PaymentPayeeAccountAddress {

    private String street;
    private String city;
    private String postalCode;

}
