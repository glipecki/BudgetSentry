package net.liepcki.budgetsentry.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.liepcki.budgetsentry.payment.PaymentDate;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInfoDto {

	String paymentId;
	String number;
	BigDecimal price;
	PaymentDate dueDate;
	PaymentDate invoiceDate;
	LocalDate paymentDate;

}
