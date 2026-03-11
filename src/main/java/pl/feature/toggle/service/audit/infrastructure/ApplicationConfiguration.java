package pl.feature.toggle.service.audit.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.handler.AuditHandlerFacade;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;

@Configuration("featureToggleAuditConfiguration")
class ApplicationConfiguration {

    @Bean
    AuditUseCase featureToggleAuditUseCase(AuditCommandRepository auditCommandRepository) {
        return AuditHandlerFacade.createAuditUseCase(auditCommandRepository);
    }
}
