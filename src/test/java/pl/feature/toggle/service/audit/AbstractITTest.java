package pl.feature.toggle.service.audit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@SpringBootTest
public abstract class AbstractITTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @DynamicPropertySource
    static void mongoProps(DynamicPropertyRegistry r) {
        var mongo = MongoContainer.getInstance();
        r.add("spring.mongodb.uri", mongo::getReplicaSetUrl);
        r.add("spring.mongodb.representation.uuid", () -> "standard");
    }

    @AfterEach
    void tearDown() {
        mongoTemplate.getDb()
                .listCollectionNames()
                .forEach(mongoTemplate::dropCollection);
    }

    protected long countProcessedEvents() {
        return mongoTemplate.getCollection("processed_events").countDocuments();
    }
}
