package net.liepcki.budgetsentry.payment;

import lombok.ToString;

/**
 * @author gregorry
 */
@ToString
public enum PaymentDefinitionPriceType {

    /**
     * same price every month or same as last month price
     */
    CONSTANT,

    /**
     * based on media usage, changes every month
     */
    VARYING

}
