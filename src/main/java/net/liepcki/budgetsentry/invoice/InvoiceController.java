package net.liepcki.budgetsentry.invoice;

import lombok.extern.slf4j.Slf4j;
import net.liepcki.budgetsentry.payment.Payment;
import net.liepcki.budgetsentry.payment.PaymentDate;
import net.liepcki.budgetsentry.payment.PaymentDateType;
import net.liepcki.budgetsentry.payment.PaymentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author gregorry
 */
@RestController
@Slf4j
public class InvoiceController {

	private final PaymentRepository paymentRepository;

	public InvoiceController(final PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
	}


	@RequestMapping(value = "/api/v1/invoice/{paymentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InvoiceInfoDto inf(@PathVariable(value = "paymentId") final String paymentId) {
		log.debug("User request for payment invovice details [paymentId={}]", paymentId);
		final Payment payment = paymentRepository.findOne(paymentId);
		return InvoiceInfoDto.builder()
				.paymentId(payment.getId())
				.number(payment.getInvoiceNumber())
				.price(payment.getPrice())
				.dueDate(payment.getPaymentDueDate())
				.invoiceDate(payment.getInvoiceDate())
				.paymentDate(payment.getPaymentDate())
				.build();
	}

	@RequestMapping(value = "/api/v1/invoice/{paymentId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String fillInvoice(@PathVariable(value = "paymentId") final String paymentId, @Valid InvoiceDto paymentInvoice) {
		log.info("User request to fill payment invoice [paymentId={}]", paymentId);
		final Payment payment = paymentRepository.findOne(paymentId);

		payment.setInvoiceNumber(paymentInvoice.getNumber());
		payment.setPrice(paymentInvoice.getPrice());
		payment.setPaymentDueDate(
				PaymentDate.builder()
						.date(paymentInvoice.getDueDate())
						.type(PaymentDateType.REAL)
						.build()
		);
		payment.setInvoiceDate(
				PaymentDate.builder()
						.date(paymentInvoice.getInvoiceDate())
						.type(PaymentDateType.REAL)
						.build()
		);

		paymentRepository.save(payment);

		return "ok";
	}

	@RequestMapping(value = "/api/v1/invoice/{paymentId}/paid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String paidInvoice(@PathVariable(value = "paymentId") final String paymentId, @Valid InvoicePaidDto invoicePaidDto) {
		log.info("User rest to fill paid invoice [paymentId={}]", paymentId);
		final Payment payment = paymentRepository.findOne(paymentId);

		payment.setPaymentDate(invoicePaidDto.getDate());

		paymentRepository.save(payment);

		return "ok";
	}

}
