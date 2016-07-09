package net.liepcki.budgetsentry.payment;

/**
 * @author gregorry
 */
public enum PaymentDefinitionPriceType {

	/**
	 * same price every month or same as last month price
	 */
	CONSTANT,

	/**
	 * based on media usage, changes every month
	 */
	VARYING

}
