package net.liepcki.budgetsentry.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePaidDto {

	@NotBlank
	LocalDate date;

	// załącznik potwierdzenia płatności

}
