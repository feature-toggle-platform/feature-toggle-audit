package pl.feature.toggle.service.audit.application.handler;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.contracts.event.projects.ProjectCreated;

import static pl.feature.toggle.service.audit.application.handler.AuditEntryFromEventMapper.buildAuditFor;

@AllArgsConstructor
class ProjectAuditHandler implements ProjectAuditUseCase {

    private final AuditRepository auditRepository;

    @Override
    public void handle(ProjectCreated event) {
        var audit = buildAuditFor(event);
        auditRepository.save(audit);
    }
}
