package com.configly.audit.infrastructure.out.db.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.configly.event.processing.api.NonTransactionalProcessedEventRepository;

@Configuration("processedEventConfig")
class RepositoryConfig {

    @Bean
    NonTransactionalProcessedEventRepository nonTransactionalProcessedEventRepository(SpringDataProcessedEventRepository springDataProcessedEventRepository) {
        return new MongoProcessedEventRepository(springDataProcessedEventRepository);
    }

}
