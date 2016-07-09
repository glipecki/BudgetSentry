package net.liepcki.budgetsentry.scheduler;

import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;

/**
 * @author gregorry
 */
@Service
public class CurrentDateProvider {

	private final Clock clock;

	public CurrentDateProvider(final Clock clock) {
		this.clock = clock;
	}

	public LocalDate getCurrentDate() {
		return LocalDate.now(clock);
	}

}
