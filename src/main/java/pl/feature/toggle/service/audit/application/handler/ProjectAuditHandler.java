package pl.feature.toggle.service.audit.application.handler;

import pl.feature.toggle.service.audit.application.port.in.ProjectAuditUseCase;
import pl.feature.toggle.service.contracts.event.projects.ProjectCreated;

import static pl.feature.toggle.service.audit.application.handler.AuditEntryFromEventMapper.buildAuditFor;

class ProjectAuditHandler implements ProjectAuditUseCase {
    @Override
    public void handle(ProjectCreated event) {
        var audit = buildAuditFor(event);
    }
}
