package net.liepcki.budgetsentry.user;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.BudgetSentryApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author gregorry
 */
@Slf4j
public class UserRepositoryTest extends BudgetSentryApplicationTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void shouldPersistAnyUser() {
		// when
		userRepository.save(User.builder().id("greg").name("Grzegorz").build());
		userRepository.save(User.builder().id("krysia").name("Krysia").build());

		// then
		final List<User> users = userRepository.findAll();
		log.debug("Users from DB: {}", users);

		assertThat(users).hasSize(2);
	}

}
