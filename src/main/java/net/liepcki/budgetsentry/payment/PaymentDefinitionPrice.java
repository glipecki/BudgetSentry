package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author gregorry
 */
@Data
@Builder
public class PaymentDefinitionPrice {

    private PaymentDefinitionPriceType type;
    private BigDecimal value;

}
