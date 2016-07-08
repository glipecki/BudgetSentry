package net.liepcki.budgetsentry.payee;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author gregorry
 */
@Document(collection = "payeeAccounts")
@Data
@Builder
public class PayeeAccount {

    @Id
    private String id;
    private String shortName;
    private String payee;
    private String isbn;
    private String user;

}
