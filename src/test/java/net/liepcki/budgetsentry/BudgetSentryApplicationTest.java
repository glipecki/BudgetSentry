package net.liepcki.budgetsentry;

import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.user.StaticCurrentUserProvider;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Clock;
import java.time.Instant;
import java.util.TimeZone;

import static org.mockito.Mockito.*;

/**
 * @author gregorry
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(alwaysPrint = false)
@Slf4j
public abstract class BudgetSentryApplicationTest {

	public static final String USER = StaticCurrentUserProvider.USER;

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	private MockMvc mockMvc;

	@Value("${local.server.port}")
	private int port;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Before
	public void cleanTestDb() {
		for (final String database : mongoClient.listDatabaseNames()) {
			log.debug("Dropping MongoDB database before test [dbName={}]", database);
			mongoClient.dropDatabase(database);
		}
	}

	protected MockMvc mockMvc() {
		return this.mockMvc;
	}

	protected TestRestTemplate restTemplate() {
		return restTemplate;
	}

	protected String path(String path) {
		return String.format("http://localhost:%d/%s", port, path.startsWith("/") ? path.substring(1) : path);
	}

	protected void mockFixedClock(final Clock mockedClock, final String date) {
		Instant instant = Instant.parse(date);
		when(mockedClock.instant()).thenReturn(instant);
		when(mockedClock.getZone()).thenReturn(TimeZone.getDefault().toZoneId());
	}

}
