package net.liepcki.budgetsentry.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author gregorry
 */
public interface UserRepository extends MongoRepository<User, String> {
}
