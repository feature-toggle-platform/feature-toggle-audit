package pl.feature.toggle.service.audit.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.feature.toggle.service.audit.application.handler.FeatureToggleAuditHandlerFacade;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;

@Configuration("featureToggleAuditConfiguration")
class Config {

    @Bean
    FeatureToggleAuditUseCase featureToggleAuditUseCase() {
        return FeatureToggleAuditHandlerFacade.createFeatureToggleAuditUseCase();
    }

    @Bean
    EnvironmentAuditUseCase environmentAuditUseCase() {
        return FeatureToggleAuditHandlerFacade.createEnvironmentAuditUseCase();
    }

    @Bean
    ProjectAuditUseCase projectAuditUseCase() {
        return FeatureToggleAuditHandlerFacade.createProjectAuditUseCase();
    }
}
