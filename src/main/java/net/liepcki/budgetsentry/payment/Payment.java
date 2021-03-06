package net.liepcki.budgetsentry.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Document(collection = "payments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

	@Id
	private String id;
	private PaymentDate invoiceDate;
	private PaymentDate paymentDueDate;
	private LocalDate paymentDate;
	private String invoiceNumber;
	private BigDecimal price;
	private PaymentPayee payee;
	private String paymentDefinition;
	private String user;

}
