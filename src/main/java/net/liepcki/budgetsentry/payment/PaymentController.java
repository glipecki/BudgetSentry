package net.liepcki.budgetsentry.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregorry
 */
@RestController
@Slf4j
public class PaymentController {

	private static final String GET_PAYMENT_URI = "/api/v1/payment/{id}";

	private final PaymentRepository paymentRepository;

	public PaymentController(final PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}

	@RequestMapping(value = GET_PAYMENT_URI, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Payment payment(@PathVariable(value = "id") final String id) {
		log.debug("User request for payment details [paymentId={}]", id);
		return paymentRepository.findOne(id);
	}

}
