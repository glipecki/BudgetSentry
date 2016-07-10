package net.liepcki.budgetsentry.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto {

	@NotBlank(message = "Invoice number must not be blank")
	String number;

	@NotBlank(message = "Invoice price must not be blank")
	BigDecimal price;

	@NotBlank(message = "Invoice due date must not be blank")
	LocalDate dueDate;

	@NotBlank(message = "Invoice date must not be blank")
	LocalDate invoiceDate;

	// załącznik faktury

}
