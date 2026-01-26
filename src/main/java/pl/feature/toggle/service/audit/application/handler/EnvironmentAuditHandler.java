package pl.feature.toggle.service.audit.application.handler;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;

import static pl.feature.toggle.service.audit.application.handler.AuditEntryFromEventMapper.buildAuditFor;

@AllArgsConstructor
class EnvironmentAuditHandler implements EnvironmentAuditUseCase {

    private final AuditRepository auditRepository;

    @Override
    public void handle(EnvironmentCreated event) {
        var audit = buildAuditFor(event);
        auditRepository.save(audit);
    }
}
