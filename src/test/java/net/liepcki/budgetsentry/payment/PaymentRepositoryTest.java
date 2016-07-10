package net.liepcki.budgetsentry.payment;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

/**
 * @author gregorry
 */
@Slf4j
public class PaymentRepositoryTest extends BudgetSentryApplicationTest {

	@Autowired
	private PaymentRepository repository;

	@Test
	public void shouldPersistPayment() {
		repository.save(
				Payment.builder()
						.id(UUID.randomUUID().toString())
						.invoiceDate(PaymentDate.builder().date(LocalDate.of(2016, 07, 06)).build())
						.paymentDueDate(PaymentDate.builder().date(LocalDate.of(2016, 07, 25)).build())
						.paymentDate(LocalDate.of(2016, 07, 19))
						.invoiceNumber("XYZ/ABCDDEFG/KKK")
						.price(new BigDecimal("229.90"))
						.payee(
								PaymentPayee.builder()
										.iban("XYZXYZXYZXYZXYZXYZXYZXYZXY")
										.name("INEA S.A.")
										.street("ul. Klaudyny Potockiej 25")
										.city("Poznań")
										.postalCode("60-211")
										.build()
						)
						.paymentDefinition("inea-ulica-zz-m-yy")
						.user("greg")
						.build()
		);

		final List<Payment> payments = repository.findAll();
		log.debug("Payments from DB: {}", payments);

		assertThat(payments).hasSize(1);
	}

}
