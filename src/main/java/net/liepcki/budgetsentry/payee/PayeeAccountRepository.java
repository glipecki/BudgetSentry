package net.liepcki.budgetsentry.payee;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author gregorry
 */
public interface PayeeAccountRepository extends MongoRepository<PayeeAccount, String> {
}
