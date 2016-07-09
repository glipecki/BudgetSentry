package net.liepcki.budgetsentry.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPayeeAccount {

	private String name;
	private PaymentPayeeAccountAddress address;

}
