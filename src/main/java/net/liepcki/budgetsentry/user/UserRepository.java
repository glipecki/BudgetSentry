package net.liepcki.budgetsentry.user;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by gregorry on 08.07.2016.
 */
public interface UserRepository extends MongoRepository<User, String> {
}
