package net.liepcki.budgetsentry.payment;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author gregorry
 */
@Document(collection = "paymentDefinitions")
@Data
@Builder
public class PaymentDefinition {

    @Id
    private String id;
    private String payeeAccount;
    private PaymentDefinitionPeriod period;
    private PaymentDefinitionPrice price;
    private PaymentDefinitionDate innvoiceDate;
    private PaymentDefinitionDate paymentDueDate;
    private String user;

}
