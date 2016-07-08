package net.liepcki.budgetsentry.payment;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gregorry
 */
@Slf4j
public class PaymentRepositoryTest extends BudgetSentryApplicationTests {

    @Autowired
    private PaymentRepository repository;

    @Test
    public void shouldPersistPayment() {
        repository.save(
                Payment.builder()
                        .id(UUID.randomUUID().toString())
                        .invoiceDate(LocalDate.of(2016, 07, 06))
                        .paymentDueDate(LocalDate.of(2016, 07, 25))
                        .paymentDate(LocalDate.of(2016, 07, 19))
                        .invoiceNumber("XYZ/ABCDDEFG/KKK")
                        .price(new BigDecimal("229.90"))
                        .payeeAccount(
                                PaymentPayeeAccount.builder()
                                        .name("INEA S.A.")
                                        .address(
                                                PaymentPayeeAccountAddress.builder()
                                                        .street("ul. Klaudyny Potockiej 25")
                                                        .city("Poznań")
                                                        .postalCode("60-211")
                                                        .build()
                                        )
                                        .build()
                        )
                        .isbn("XYZXYZXYZXYZXYZXYZXYZXYZXY")
                        .paymentDefinition("inea-ulica-zz-m-yy")
                        .user("greg")
                        .build()
        );

        final List<Payment> payments = repository.findAll();
        log.debug("Payments from DB: {}", payments);

        assertThat(payments).hasSize(1);
    }

}
