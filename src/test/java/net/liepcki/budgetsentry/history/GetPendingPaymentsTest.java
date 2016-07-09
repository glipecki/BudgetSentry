package net.liepcki.budgetsentry.history;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTest;
import net.liepcki.budgetsentry.payment.Payment;
import net.liepcki.budgetsentry.payment.PaymentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author gregorry
 */
@Slf4j
public class GetPendingPaymentsTest extends BudgetSentryApplicationTest {

    /**
     * @deprecated use REST API instead of repository
     */
    @Autowired
    @Deprecated
    private PaymentRepository paymentRepository;

    /**
     * Just demo method for Spring Test MockMVC.
     *
     * @throws Exception
     */
    @Test
    public void shouldGetPendingPaymentsMockMvc() throws Exception {
        mockMvc().perform(get(HistoryController.PENDING_PAYMENTS_URI)).andExpect(status().isOk());
    }

    @Test
    public void shouldGetPendingPayments() {
        // given
        final String paymentId = UUID.randomUUID().toString();
        paymentRepository.save(
                Payment.builder()
                        .id(paymentId)
                        .user(USER)
                        .build()
        );

        // when
        final Payment[] result = restTemplate().getForObject(path(HistoryController.PENDING_PAYMENTS_URI), Payment[].class);
        log.debug("Pending payments result: {}", Arrays.toString(result));

        // then
        assertThat(result).hasSize(1);
        assertThat(result[0].getId()).isEqualTo(paymentId);
    }

}
