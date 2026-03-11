package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;

@Configuration("auditConfig")
class Config {

    @Bean
    AuditCommandRepository auditCommandRepository(SpringDataAuditEntryMongoRepository mongoRepository) {
        return new MongoAuditCommandRepository(mongoRepository);
    }

    @Bean
    AuditQueryRepository auditQueryRepository(SpringDataAuditEntryMongoRepository mongoRepository) {
        return new MongoAuditQueryRepository(mongoRepository);
    }
}
