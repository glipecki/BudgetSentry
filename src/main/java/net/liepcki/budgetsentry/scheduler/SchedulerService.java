package net.liepcki.budgetsentry.scheduler;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payment.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    private final CurrentDateProvider currentDateProvider;

    public SchedulerService(
            final PaymentDefinitionRepository paymentDefinitionRepository,
            final PaymentRepository paymentRepository,
            final CurrentDateProvider currentDateProvider) {
        this.paymentDefinitionRepository = paymentDefinitionRepository;
        this.paymentRepository = paymentRepository;
        this.currentDateProvider = currentDateProvider;
    }

    public void schedule() {
        log.info("System request to schedule pending payments");

        final LocalDate currentDate = currentDateProvider.getCurrentDate();
        log.debug("Looking for potential payments for based on day: {}", currentDate);

        // look for payments within next month
        final List<Payment> existingPayments = paymentRepository.findByPaymentDueDateDateBetween(
                currentDate.minus(Period.ofDays(1)),
                currentDate.plus(Period.ofMonths(1))
        );
        log.debug("Payments within next month: {}", existingPayments);

        // look for missing payments for next month
        final List<String> paymentDefinitionsWithExistingPayments = existingPayments.stream().map(Payment::getPaymentDefinition).collect(Collectors.toList());
        final List<PaymentDefinition> paymentDefinitionsForMissingPayments = paymentDefinitionRepository.findByIdNotIn(paymentDefinitionsWithExistingPayments);
        log.debug("Missing payments for next month: {}", paymentDefinitionsForMissingPayments);

        // create missing payments
        for (final PaymentDefinition paymentDefinition : paymentDefinitionsForMissingPayments) {
            paymentRepository.save(
                    Payment.builder()
                            .id(UUID.randomUUID().toString())
                            .paymentDefinition(paymentDefinition.getId())
                            .paymentDueDate(
                                    PaymentDate.builder()
                                            .type(PaymentDateType.PREDICTED)
                                            .date(
                                                    LocalDate.of(
                                                            currentDate.getYear(),
                                                            paymentDefinition.getPaymentDueDate().getDay() >= currentDate.getDayOfMonth() ? currentDate.getMonth() : currentDate.plus(Period.ofMonths(1)).getMonth(),
                                                            paymentDefinition.getPaymentDueDate().getDay())
                                            )
                                            .build()
                            )
                            .price(paymentDefinition.getPrice().getValue())
                            .build()
            );
        }
    }

}
