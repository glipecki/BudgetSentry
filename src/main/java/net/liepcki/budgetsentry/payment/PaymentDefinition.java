package net.liepcki.budgetsentry.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author gregorry
 */
@Document(collection = "paymentDefinitions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDefinition {

	@Id
	private String id;
	private String payeeAccount;
	private PaymentDefinitionPeriod period;
	private PaymentDefinitionPrice price;
	private PaymentDefinitionDate invoiceDate;
	private PaymentDefinitionDate paymentDueDate;
	private String user;

}
