package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeatureToggleAuditHandlerFacade {

    public static FeatureToggleAuditUseCase createFeatureToggleAuditUseCase() {
        return new FeatureToggleAuditHandler();
    }

    public static EnvironmentAuditUseCase createEnvironmentAuditUseCase() {
        return new EnvironmentAuditHandler();
    }

    public static ProjectAuditUseCase createProjectAuditUseCase() {
        return new ProjectAuditHandler();
    }

}
