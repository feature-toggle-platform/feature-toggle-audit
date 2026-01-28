package pl.feature.toggle.service.audit;

import org.testcontainers.mongodb.MongoDBContainer;

class MongoContainer {

    private static final MongoDBContainer INSTANCE =
            new MongoDBContainer("mongo:8.2.3");

    static {
        INSTANCE.start();
    }

    static MongoDBContainer getInstance() {
        return INSTANCE;
    }

}
