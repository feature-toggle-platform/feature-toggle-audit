package com.configly.audit.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.time.ZoneOffset.UTC;

public record AuditTime(
        LocalDateTime occurredAt
) {

    public static AuditTime from(LocalDateTime occurredAt) {
        return new AuditTime(occurredAt.truncatedTo(ChronoUnit.SECONDS));
    }

    public static AuditTime from(Instant instant) {
        return from(LocalDateTime.ofInstant(instant, UTC));
    }

    public Instant toInstant() {
        return occurredAt.toInstant(UTC);
    }

}
