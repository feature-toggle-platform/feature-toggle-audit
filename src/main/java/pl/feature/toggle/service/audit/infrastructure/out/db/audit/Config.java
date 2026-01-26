package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;

@Configuration("auditConfig")
class Config {

    @Bean
    AuditRepository auditRepository(SpringDataAuditEntryMongoRepository mongoRepository) {
        return new MongoAuditRepository(mongoRepository);
    }
}
