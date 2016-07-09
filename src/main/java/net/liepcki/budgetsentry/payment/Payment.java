package net.liepcki.budgetsentry.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
