package pl.feature.toggle.service.audit.application.handler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import pl.feature.toggle.service.audit.application.port.in.AuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentCreated;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentStatusChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentTypeChanged;
import pl.feature.toggle.service.contracts.event.environment.EnvironmentUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleStatusChanged;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleValueChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectCreated;
import pl.feature.toggle.service.contracts.event.project.ProjectStatusChanged;
import pl.feature.toggle.service.contracts.event.project.ProjectUpdated;

import static pl.feature.toggle.service.audit.application.handler.AuditBuilder.buildAuditFor;

@AllArgsConstructor
@Slf4j
class AuditUseCaseHandler implements AuditUseCase {

    private final AuditCommandRepository auditCommandRepository;

    @Override
    public void handle(FeatureToggleCreated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=FeatureToggleCreated featureToggleId={}", event.id());
    }

    @Override
    public void handle(FeatureToggleUpdated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=FeatureToggleUpdated featureToggleId={}", event.id());
    }

    @Override
    public void handle(FeatureToggleStatusChanged event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=FeatureToggleStatusChanged featureToggleId={}", event.id());
    }

    @Override
    public void handle(FeatureToggleValueChanged event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=FeatureToggleValueChanged featureToggleId={}", event.id());
    }

    @Override
    public void handle(ProjectCreated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=ProjectCreated projectId={}", event.projectId());
    }

    @Override
    public void handle(ProjectStatusChanged event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=ProjectStatusChanged projectId={}", event.projectId());
    }

    @Override
    public void handle(ProjectUpdated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=ProjectUpdated projectId={}", event.projectId());
    }

    @Override
    public void handle(EnvironmentCreated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=EnvironmentCreated environmentId={}", event.environmentId());
    }

    @Override
    public void handle(EnvironmentUpdated event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=EnvironmentUpdated environmentId={}", event.environmentId());
    }

    @Override
    public void handle(EnvironmentStatusChanged event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=EnvironmentStatusChanged environmentId={}", event.environmentId());
    }

    @Override
    public void handle(EnvironmentTypeChanged event) {
        var audit = buildAuditFor(event);
        auditCommandRepository.save(audit);
        log.info("Audit entry created: event=EnvironmentTypeChanged environmentId={}", event.environmentId());
    }
}
