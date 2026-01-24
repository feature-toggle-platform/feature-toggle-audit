package pl.feature.toggle.service.audit.application.handler;

import pl.feature.toggle.service.audit.application.port.in.EnvironmentAuditUseCase;
import pl.feature.toggle.service.audit.domain.AuditEntry;
import pl.feature.toggle.service.contracts.event.projects.EnvironmentCreated;

import static pl.feature.toggle.service.audit.application.handler.AuditEntryFromEventMapper.buildAuditFor;

class EnvironmentAuditHandler implements EnvironmentAuditUseCase {

    @Override
    public void handle(EnvironmentCreated event) {
        var audit = buildAuditFor(event);

    }
}
