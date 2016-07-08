package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
public class PaymentDefinitionPeriod {

    private PaymentDefinitionPeriodType type;
    private LocalDate startFrom;

}
