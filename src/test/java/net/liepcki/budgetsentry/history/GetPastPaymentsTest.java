package net.liepcki.budgetsentry.history;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTests;
import net.liepcki.budgetsentry.payment.Payment;
import net.liepcki.budgetsentry.payment.PaymentRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gregorry
 */
@Slf4j
public class GetPastPaymentsTest extends BudgetSentryApplicationTests {

    @Before
    public void setUpRepository() {
        paymentRepository.save(finishedPayment("finished"));
        paymentRepository.save(pendingPayment("pending"));
    }

    @Test
    public void shouldGetPastPayments() {
        // when
        final List<Payment> pastPayments = service.getPastPayments();

        // then
        log.debug("Past payments: {}", pastPayments);
        assertThat(pastPayments).isNotEmpty().hasSize(1);
        assertThat(pastPayments.get(0).getId()).isEqualTo("finished");
    }

    @Test
    public void shouldGetPendingPayments() {
        // when
        final List<Payment> pendingPayments = service.getPendingPayments();

        // then
        log.debug("Pending payments: {}", pendingPayments);
        assertThat(pendingPayments).isNotEmpty().hasSize(1);
        assertThat(pendingPayments.get(0).getId()).isEqualTo("pending");
    }

    private Payment pendingPayment(final String id) {
        return dummyPayment(id)
                .build();
    }

    private Payment finishedPayment(final String id) {
        return dummyPayment(id)
                .paymentDate(LocalDate.now())
                .build();
    }

    private Payment.PaymentBuilder dummyPayment(final String id) {
        return Payment.builder()
                .id(id)
                .user(USER);
    }

    @Autowired
    private HistoryService service;

    @Autowired
    private PaymentRepository paymentRepository;

}
