package net.liepcki.budgetsentry.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payee.Payee;
import net.liepcki.budgetsentry.payee.PayeeAccount;
import net.liepcki.budgetsentry.payee.PayeeAccountRepository;
import net.liepcki.budgetsentry.payee.PayeeRepository;
import net.liepcki.budgetsentry.payment.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
				paymentRepository.save(
						createNextPayment(currentDate, paymentDefinition, getNextPaymentDate(paymentDefinition, currentDate, lastPayment))
				);
			}
		}

	}

	private LocalDate getNextPaymentDate(final PaymentDefinition paymentDefinition, final LocalDate currentDate, final Payment lastPayment) {
		if (lastPayment != null) {
			final LocalDate lastPaymentDate = lastPayment.getPaymentDueDate().getDate();
			final LocalDate normalizedLastPaymentDate = LocalDate.of(lastPaymentDate.getYear(), lastPaymentDate.getMonth(), paymentDefinition.getPaymentDueDate().getDay());
			final Period paymentPeriod = paymentDefinition.getPeriod().getType().getPeriod();
			return normalizedLastPaymentDate.plus(paymentPeriod);
		} else {
			final LocalDate startDate = paymentDefinition.getPeriod().getStartFrom();
			return LocalDate.of(startDate.getYear(), startDate.getMonth(), paymentDefinition.getPaymentDueDate().getDay());
		}
	}

	private Payment createNextPayment(final LocalDate currentDate, final PaymentDefinition paymentDefinition, final LocalDate nextPaymentDate) {
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
