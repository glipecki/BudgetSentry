package net.liepcki.budgetsentry.payee;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author gregorry
 */
@Slf4j
public class PayeeRepositoryTest extends BudgetSentryApplicationTest {

	@Autowired
	private PayeeRepository payeeRepository;

	@Autowired
	private MongoTemplate mongo;

	@Test
	public void shouldStorePayee() {
		payeeRepository.save(
				Payee.builder()
						.id("inea")
						.shortName("Inea")
						.name("INEA S.A.")
						.user("greg")
						.address(
								PayeeAddress.builder()
										.street("ul. Klaudyny Potockiej 25")
										.city("Poznań")
										.postalCode("60-211")
										.build()
						)
						.build()
		);

		final List<Payee> payees = payeeRepository.findAll();
		log.debug("Payees from DB: {}", payees);
		assertThat(payees).hasSize(1);

		log.debug("OneRow: {}", mongo.getCollection("payees").findOne());
	}

}
