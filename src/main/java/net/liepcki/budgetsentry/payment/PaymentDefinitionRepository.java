package net.liepcki.budgetsentry.payment;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author gregorry
 */
public interface PaymentDefinitionRepository extends MongoRepository<PaymentDefinition, String> {
}
