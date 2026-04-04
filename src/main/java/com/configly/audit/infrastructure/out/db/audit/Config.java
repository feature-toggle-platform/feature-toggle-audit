package com.configly.audit.infrastructure.out.db.audit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.configly.audit.application.port.out.AuditCommandRepository;
import com.configly.audit.application.port.out.AuditQueryRepository;

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
