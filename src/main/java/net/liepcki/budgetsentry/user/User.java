package net.liepcki.budgetsentry.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author gregorry
 */
@Document(collection = "users")
@Data
@Builder
public class User {

    @Id
    private String id;
    private String name;

}