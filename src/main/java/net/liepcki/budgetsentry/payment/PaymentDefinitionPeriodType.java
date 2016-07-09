package net.liepcki.budgetsentry.payment;

import java.time.Period;

/**
 * @author gregorry
 */
public enum PaymentDefinitionPeriodType {

	/**
	 * each month
	 */
	MONTHLY(Period.ofMonths(1)),

	/**
	 * every two months
	 */
	BIMONTHLY(Period.ofMonths(2));

	private Period period;

	PaymentDefinitionPeriodType(final Period period) {
		this.period = period;
	}

	public Period getPeriod() {
		return period;
	}

}
