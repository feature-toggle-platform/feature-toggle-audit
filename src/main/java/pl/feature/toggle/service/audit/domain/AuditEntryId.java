package pl.feature.toggle.service.audit.domain;

import java.util.UUID;

public record AuditEntryId(
        String value
) {

    public static AuditEntryId create() {
        return new AuditEntryId(UUID.randomUUID().toString());
    }
}
