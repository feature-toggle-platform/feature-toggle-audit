package pl.feature.toggle.service.audit.application.port.in;

import pl.feature.toggle.service.contracts.event.projects.ProjectCreated;

public interface ProjectAuditUseCase {

    void handle(ProjectCreated event);
}
