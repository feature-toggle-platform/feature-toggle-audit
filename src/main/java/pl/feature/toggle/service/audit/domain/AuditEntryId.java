package pl.feature.toggle.service.audit.domain;

import java.util.UUID;

public record AuditEntryId(
        String value
) {

    public static AuditEntryId create() {
        return new AuditEntryId(UUID.randomUUID().toString());
    }

    public static AuditEntryId of(String value){
        return new AuditEntryId(value);
    }
}
