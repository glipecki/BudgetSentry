package net.liepcki.budgetsentry.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDefinitionPeriod {

	private PaymentDefinitionPeriodType type;
	private LocalDate startFrom;

}
