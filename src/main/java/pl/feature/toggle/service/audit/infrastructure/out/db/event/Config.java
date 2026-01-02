package pl.feature.toggle.service.audit.infrastructure.out.db.event;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.port.out.ProcessedEventRepository;

@Configuration("processedEventConfig")
class Config {

    @Bean
    ProcessedEventRepository processedEventRepository(SpringDataProcessedEventRepository springDataProcessedEventRepository) {
        return new MongoProcessedEventRepository(springDataProcessedEventRepository);
    }

}
