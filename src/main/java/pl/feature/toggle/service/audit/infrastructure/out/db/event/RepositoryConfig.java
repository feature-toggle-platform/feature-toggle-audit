package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.event.processing.api.NonTransactionalProcessedEventRepository;
import pl.feature.toggle.service.event.processing.api.ProcessedEventRepository;

@Configuration("processedEventConfig")
class RepositoryConfig {

    @Bean
    NonTransactionalProcessedEventRepository nonTransactionalProcessedEventRepository(SpringDataProcessedEventRepository springDataProcessedEventRepository) {
        return new MongoProcessedEventRepository(springDataProcessedEventRepository);
    }

}
