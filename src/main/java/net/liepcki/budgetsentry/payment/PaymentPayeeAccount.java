package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;

/**
 * @author gregorry
 */
@Data
@Builder
public class PaymentPayeeAccount {

    private String name;
    private PaymentPayeeAccountAddress address;

}
