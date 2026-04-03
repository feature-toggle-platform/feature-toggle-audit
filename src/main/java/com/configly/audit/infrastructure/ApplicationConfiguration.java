package com.configly.audit.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.configly.audit.application.handler.AuditHandlerFacade;
import com.configly.audit.application.port.in.AuditUseCase;
import com.configly.audit.application.port.out.AuditCommandRepository;

@Configuration("featureToggleAuditConfiguration")
class ApplicationConfiguration {

    @Bean
    AuditUseCase featureToggleAuditUseCase(AuditCommandRepository auditCommandRepository) {
        return AuditHandlerFacade.createAuditUseCase(auditCommandRepository);
    }
}
