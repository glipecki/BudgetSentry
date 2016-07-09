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
public class PaymentPayee {

	private String name;
	private String street;
	private String city;
	private String postalCode;
	private String iban;

}
