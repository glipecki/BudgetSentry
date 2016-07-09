package net.liepcki.budgetsentry;

import com.mongodb.MongoClientOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

/**
 * @author gregorry
 */
@SpringBootApplication
public class BudgetSentryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetSentryApplication.class, args);
	}

	@Bean
	public MongoClientOptions optionsProvider() {
		return MongoClientOptions.builder().serverSelectionTimeout(5000).build();
	}

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}
