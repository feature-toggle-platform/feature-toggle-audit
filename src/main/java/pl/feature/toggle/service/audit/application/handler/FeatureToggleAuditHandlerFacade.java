package pl.feature.toggle.service.audit.application.handler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeatureToggleAuditHandlerFacade {

    public static FeatureToggleAuditUseCase createFeatureToggleAuditUseCase(AuditRepository auditRepository) {
        return new FeatureToggleAuditHandler(auditRepository);
    }

    public static EnvironmentAuditUseCase createEnvironmentAuditUseCase(AuditRepository auditRepository) {
        return new EnvironmentAuditHandler(auditRepository);
    }

    public static ProjectAuditUseCase createProjectAuditUseCase(AuditRepository auditRepository) {
        return new ProjectAuditHandler(auditRepository);
    }

}
