package net.liepcki.budgetsentry.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTest;
import net.liepcki.budgetsentry.payment.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static net.liepcki.budgetsentry.payment.PaymentDefinitionPeriodType.MONTHLY;
import static org.assertj.core.api.Assertions.*;

/**
 * @author gregorry
 */
@Slf4j
public class ScheduleMonthlyPaymentTest extends BudgetSentryApplicationTest {

	@Autowired
	private SchedulerService scheduler;

	/**
	 * @deprecated use REST API instead of repository
	 */
	@Autowired
	@Deprecated
	private PaymentDefinitionRepository paymentDefinitionRepository;

	/**
	 * @deprecated use REST API instead of repository
	 */
	@Autowired
	@Deprecated
	private PaymentRepository paymentRepository;

	@MockBean
	private Clock clock;

	// monthly constant payment, alternative use last month payment price for constant
	// monthly average payment
	// bimonthly constant
	// bimonthly average

	@Test
	public void shouldScheduleConstantMonthlyPayment() {
		// given
		mockFixedClock(clock, "2016-07-01T00:00:00Z");
		final String paymentDefinitionId = paymentDefinitionRepository.save(
				paymentDefinition(MONTHLY, 19)
		).getId();

		// when
		scheduler.schedule();

		// then
		final List<Payment> payments = paymentRepository.findAll();
		for (final Payment payment : payments) {
			log.debug("Current payments: {}", payment);
		}

		assertThat(payments).hasSize(1);

		final Payment payment = payments.get(0);
		assertThat(payment.getPaymentDefinition()).isEqualTo(paymentDefinitionId);
	}

	@Test
	public void shouldNotAddPaymentIfAlreadyExists() {
		// given
		mockFixedClock(clock, "2016-07-01T00:00:00Z");
		final String existingPaymentDefinitionId = paymentDefinitionRepository.save(
				paymentDefinition(MONTHLY, 10)
		).getId();
		final String newPaymentDefinitionId = paymentDefinitionRepository.save(
				paymentDefinition(MONTHLY, 10)
		).getId();
		paymentRepository.save(
				payment(existingPaymentDefinitionId, LocalDate.of(2016, 7, 10))
		);

		// when
		scheduler.schedule();

		// then
		final List<Payment> payments = paymentRepository.findAll();
		for (final Payment payment : payments) {
			log.debug("Current payments: {}", payment);
		}

		assertThat(payments).hasSize(2);
		assertThat(payments.get(0).getPaymentDefinition()).isEqualTo(existingPaymentDefinitionId);
		assertThat(payments.get(1).getPaymentDefinition()).isEqualTo(newPaymentDefinitionId);
	}

	private Payment payment(final String paymentId, final LocalDate dueDate) {
		return Payment.builder()
				.id(UUID.randomUUID().toString())
				.paymentDueDate(PaymentDate.builder().date(dueDate).build())
				.paymentDefinition(paymentId)
				.build();
	}

	private PaymentDefinition paymentDefinition(
			final PaymentDefinitionPeriodType periodType,
			final int paymentDueDate) {
		return PaymentDefinition.builder()
				.id(UUID.randomUUID().toString())
				.period(
						PaymentDefinitionPeriod.builder()
								.startFrom(LocalDate.of(2000, 1, 1))
								.type(periodType)
								.build()
				)
				.price(
						PaymentDefinitionPrice.builder()
								.type(PaymentDefinitionPriceType.CONSTANT)
								.value(new BigDecimal("100.00"))
								.build()
				)
				.invoiceDate(PaymentDefinitionDate.builder().day(1).build())
				.paymentDueDate(PaymentDefinitionDate.builder().day(paymentDueDate).build())
				.user(USER)
				.build();
	}

}
