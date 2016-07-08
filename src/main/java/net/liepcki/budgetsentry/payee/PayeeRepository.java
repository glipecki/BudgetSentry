package net.liepcki.budgetsentry.payee;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author gregorry
 */
public interface PayeeRepository extends MongoRepository<Payee, String> {
}
