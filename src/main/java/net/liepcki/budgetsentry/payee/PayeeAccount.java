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
@Document(collection = "payeeAccounts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayeeAccount {

	@Id
	private String id;
	private String shortName;
	private String payee;
	private String iban;
	private String user;

}
