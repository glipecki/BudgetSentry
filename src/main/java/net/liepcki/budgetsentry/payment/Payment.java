package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
public class Payment {

    @Id
    private String id;
    private LocalDate invoiceDate;
    private LocalDate paymentDueDate;
    private LocalDate paymentDate;
    private String invoiceNumber;
    private BigDecimal price;
    private PaymentPayeeAccount payeeAccount;
    private String isbn;
    private String paymentDefinition;
    private String user;

}
