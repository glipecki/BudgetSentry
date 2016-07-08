package net.liepcki.budgetsentry;

import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gregorry
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public abstract class BudgetSentryApplicationTests {

    public static final String USER = "dummy-user";

    @Before
    public void cleanTestDb() {
        for (final String database : mongoClient.listDatabaseNames()) {
            log.debug("Dropping MongoDB database before test [dbName={}]", database);
            mongoClient.dropDatabase(database);
        }
    }

    @Autowired
    private MongoClient mongoClient;

}
