package pl.feature.toggle.service.audit.application.handler;

import lombok.AllArgsConstructor;
import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;

import static pl.feature.toggle.service.audit.application.handler.Mapper.buildAuditFor;

@AllArgsConstructor
class FeatureToggleAuditHandler implements FeatureToggleAuditUseCase {

    private final AuditRepository auditRepository;

    @Override
    public void handle(FeatureToggleCreated event) {
        var audit = buildAuditFor(event);
        auditRepository.save(audit);
    }

    @Override
    public void handle(FeatureToggleUpdated event) {
        var audit = buildAuditFor(event);
        auditRepository.save(audit);
    }

    @Override
    public void handle(FeatureToggleDeleted event) {
        var audit = buildAuditFor(event);
        auditRepository.save(audit);
    }
}
