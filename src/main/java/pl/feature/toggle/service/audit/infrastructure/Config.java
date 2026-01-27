package pl.feature.toggle.service.audit.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.handler.FeatureToggleAuditHandlerFacade;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;

@Configuration("featureToggleAuditConfiguration")
class Config {

    @Bean
    FeatureToggleAuditUseCase featureToggleAuditUseCase(AuditRepository auditRepository) {
        return FeatureToggleAuditHandlerFacade.createFeatureToggleAuditUseCase(auditRepository);
    }

    @Bean
    EnvironmentAuditUseCase environmentAuditUseCase(AuditRepository auditRepository) {
        return FeatureToggleAuditHandlerFacade.createEnvironmentAuditUseCase(auditRepository);
    }

    @Bean
    ProjectAuditUseCase projectAuditUseCase(AuditRepository auditRepository) {
        return FeatureToggleAuditHandlerFacade.createProjectAuditUseCase(auditRepository);
    }
}
