package pl.feature.toggle.service.audit.application.handler;

import pl.feature.toggle.service.audit.application.port.in.FeatureToggleAuditUseCase;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleCreated;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleDeleted;
import pl.feature.toggle.service.contracts.event.featuretoggle.FeatureToggleUpdated;

import static pl.feature.toggle.service.audit.application.handler.AuditEntryFromEventMapper.buildAuditFor;

class FeatureToggleAuditHandler implements FeatureToggleAuditUseCase {
    @Override
    public void handle(FeatureToggleCreated event) {
        var audit = buildAuditFor(event);
    }

    @Override
    public void handle(FeatureToggleUpdated event) {
        var audit = buildAuditFor(event);
    }

    @Override
    public void handle(FeatureToggleDeleted event) {
        var audit = buildAuditFor(event);
    }
}
