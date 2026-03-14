package pl.feature.toggle.service.audit.domain;

import java.util.UUID;

public record AuditContext(
        UUID projectId,
        UUID environmentId,
        String correlationId
) {

    public static AuditContext forProject(UUID projectId, String correlationId) {
        return new AuditContext(projectId, null, correlationId);
    }

    public static AuditContext forEnvironment(UUID projectId, UUID environmentId, String correlationId) {
        return new AuditContext(projectId, environmentId, correlationId);
    }

    public static AuditContext forFeatureToggle(UUID environmentId, String correlationId) {
        return new AuditContext(null, environmentId, correlationId);
    }

    public static AuditContext build(UUID projectId, UUID environmentId, String correlationId) {
        return new AuditContext(projectId, environmentId, correlationId);
    }

}
