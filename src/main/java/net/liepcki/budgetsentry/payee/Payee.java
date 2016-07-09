package net.liepcki.budgetsentry.payee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author gregorry
 */
@Document(collection = "payees")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payee {

    @Id
    private String id;
    private String name;
    private String shortName;
    private PayeeAddress address;
    private String user;

}
