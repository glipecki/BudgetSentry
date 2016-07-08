package net.liepcki.budgetsentry.user;

import net.liepcki.budgetsentry.BudgetSentryApplicationTests;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by gregorry on 08.07.2016.
 */
public class UserRepositoryTest extends BudgetSentryApplicationTests {

    @Test
    public void shouldPersistAnyUser() {
        // when
        userRepository.save(new User("greg", "Grzegorz"));
        userRepository.save(new User("krysia", "Krysia"));

        // then
        final List<User> users = userRepository.findAll();
        log.debug("Users from DB: {}", users);

        assertThat(users).hasSize(2);
    }

    private static final Logger log = LoggerFactory.getLogger(BudgetSentryApplicationTests.class);

    @Autowired
    private UserRepository userRepository;

}
