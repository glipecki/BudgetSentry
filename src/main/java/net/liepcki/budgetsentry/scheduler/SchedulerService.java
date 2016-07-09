package net.liepcki.budgetsentry.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payee.Payee;
import net.liepcki.budgetsentry.payee.PayeeAccount;
import net.liepcki.budgetsentry.payee.PayeeAccountRepository;
import net.liepcki.budgetsentry.payee.PayeeRepository;
import net.liepcki.budgetsentry.payment.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Schedules future payments based on current date and available payment definitions.
 *
 * @author gregorry
 */
@Service
@Slf4j
public class SchedulerService {

	private final PaymentDefinitionRepository paymentDefinitionRepository;

	private final PaymentRepository paymentRepository;

	private final PayeeAccountRepository payeeAccountRepository;

	private final PayeeRepository payeeRepository;

	private final CurrentDateProvider currentDateProvider;

	public SchedulerService(
			final PaymentDefinitionRepository paymentDefinitionRepository,
			final PaymentRepository paymentRepository,
			final PayeeAccountRepository payeeAccountRepository,
			final PayeeRepository payeeRepository,
			final CurrentDateProvider currentDateProvider) {
		this.paymentDefinitionRepository = paymentDefinitionRepository;
		this.paymentRepository = paymentRepository;
		this.payeeAccountRepository = payeeAccountRepository;
		this.payeeRepository = payeeRepository;
		this.currentDateProvider = currentDateProvider;
	}

	public void schedule() {
		log.info("System request to schedule pending payments");

		final LocalDate currentDate = currentDateProvider.getCurrentDate();
		log.debug("Looking for potential payments for based on day: {}", currentDate);

		for (final PaymentDefinition paymentDefinition : paymentDefinitionRepository.findAll()) {
			final Payment lastPayment = paymentRepository.findFirstByPaymentDefinitionOrderByPaymentDueDateDateDesc(paymentDefinition.getId());
			if (lastPayment == null || currentDate.isAfter(lastPayment.getPaymentDueDate().getDate())) {
				final LocalDate startDate = getStartDate(paymentDefinition, lastPayment);
				final List<LocalDate> paymentsToAdd = getDatesInRange(startDate, currentDate, paymentDefinition.getPeriod().getType(), lastPayment == null);
				for (final LocalDate paymentToAdd : paymentsToAdd) {
					final Payment payment = createNextPayment(paymentDefinition, paymentToAdd);
					log.info("Creating new payment [paymentId: {}]", payment.getId());
					paymentRepository.save(payment);
				}
			}
		}
	}

	private LocalDate getStartDate(final PaymentDefinition paymentDefinition, final Payment lastPayment) {
		int paymentDay = paymentDefinition.getPaymentDueDate().getDay();
		final LocalDate fromDate = lastPayment != null ? lastPayment.getPaymentDueDate().getDate() : paymentDefinition.getPeriod().getStartFrom();
		final LocalDate startDate = LocalDate.of(fromDate.getYear(), fromDate.getMonth(), paymentDay);
		if (paymentDay >= startDate.getDayOfMonth()) {
			return startDate;
		} else {
			return startDate.plus(paymentDefinition.getPeriod().getType().getPeriod());
		}
	}

	private List<LocalDate> getDatesInRange(
			final LocalDate startDate,
			final LocalDate endDate,
			final PaymentDefinitionPeriodType periodType,
			final boolean addStartDate) {
		final List<LocalDate> dates = new ArrayList<>();

		LocalDate currentDate = startDate;
		if (addStartDate) {
			dates.add(currentDate);
		}
		while (currentDate.isBefore(endDate)) {
			currentDate = currentDate.plus(periodType.getPeriod());
			dates.add(currentDate);
		}

		return dates;
	}

	private Payment createNextPayment(final PaymentDefinition paymentDefinition, final LocalDate nextPaymentDate) {
		final PayeeAccount payeeAccount = payeeAccountRepository.findOne(paymentDefinition.getPayeeAccount());
		final Payee payee = payeeRepository.findOne(payeeAccount.getPayee());
		return Payment.builder()
				.id(UUID.randomUUID().toString())
				.paymentDefinition(paymentDefinition.getId())
				.invoiceDate(
						PaymentDate.builder()
								.date(nextPaymentDate.withDayOfMonth(paymentDefinition.getInvoiceDate().getDay()))
								.type(PaymentDateType.PREDICTED)
								.build()
				)
				.paymentDueDate(
						PaymentDate.builder()
								.type(PaymentDateType.PREDICTED)
								.date(nextPaymentDate)
								.build()
				)
				.payee(
						PaymentPayee.builder()
								.iban(payeeAccount.getIban())
								.name(payee.getName())
								.street(payee.getAddress().getStreet())
								.city(payee.getAddress().getCity())
								.postalCode(payee.getAddress().getPostalCode())
								.build()
				)
				.price(paymentDefinition.getPrice().getValue())
				.user(paymentDefinition.getUser())
				.build();
	}

}
