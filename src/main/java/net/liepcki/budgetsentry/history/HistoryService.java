package net.liepcki.budgetsentry.history;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payment.Payment;
import net.liepcki.budgetsentry.payment.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Allows to browse past payments.
 *
 * @author gregorry
 */
@Service
@Slf4j
public class HistoryService {

	private PaymentRepository paymentRepository;

	public HistoryService(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	public List<Payment> getPastPayments(final String user) {
		return paymentRepository.findByUserAndPaymentDateNotNull(user);
	}

	public List<Payment> getPendingPayments(final String user) {
		return paymentRepository.findByUserAndPaymentDateNull(user);
	}

}
