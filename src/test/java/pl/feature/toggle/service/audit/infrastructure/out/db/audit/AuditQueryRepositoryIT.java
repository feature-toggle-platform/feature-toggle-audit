package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.feature.toggle.service.audit.AbstractITTest;
import pl.feature.toggle.service.audit.TestAuditData;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;
import pl.feature.toggle.service.audit.domain.AuditEntryId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AuditQueryRepositoryIT extends AbstractITTest {

    @Autowired
    private AuditQueryRepository sut;

    @Autowired
    private AuditCommandRepository commandRepository;


    @Test
    void should_find_audit_entry_by_id() {
        // given
        var auditEntry = TestAuditData.randomAuditEntry();
        commandRepository.save(auditEntry);

        // when
        var result = sut.findById(auditEntry.id());

        // then
        assertThat(result.id()).isEqualTo(auditEntry.id());
        assertThat(result.type()).isEqualTo(auditEntry.type());
        assertThat(result.target()).isEqualTo(auditEntry.target());
        assertThat(result.context()).isEqualTo(auditEntry.context());
        assertThat(result.changes()).isEqualTo(auditEntry.changes());
        assertThat(result.actor()).isEqualTo(auditEntry.actor());
        assertThat(result.time()).isEqualTo(auditEntry.time());
    }

    @Test
    void should_throw_exception_when_audit_entry_does_not_exist() {
        // given
        var notExistingId = AuditEntryId.create();

        // when / then
        assertThatThrownBy(() -> sut.findById(notExistingId))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Audit entry not found");
    }
}
