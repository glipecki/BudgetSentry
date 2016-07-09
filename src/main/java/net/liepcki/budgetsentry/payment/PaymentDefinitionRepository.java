package net.liepcki.budgetsentry.payment;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author gregorry
 */
public interface PaymentDefinitionRepository extends MongoRepository<PaymentDefinition, String> {

	List<PaymentDefinition> findByIdNotIn(Collection<String> ids);

}
