package net.liepcki.budgetsentry.payment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author gregorry
 */
public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByPaymentDateNotNull();

    List<Payment> findByPaymentDateNull();
}
