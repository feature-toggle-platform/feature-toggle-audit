package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.feature.toggle.service.audit.AbstractITTest;
import pl.feature.toggle.service.audit.TestAuditData;
import pl.feature.toggle.service.audit.application.port.out.AuditCommandRepository;
import pl.feature.toggle.service.audit.application.port.out.AuditQueryRepository;

import static org.assertj.core.api.Assertions.assertThat;

class AuditCommandRepositoryIT extends AbstractITTest {

    @Autowired
    private AuditCommandRepository sut;

    @Autowired
    private AuditQueryRepository queryRepository;

    @Test
    void should_save_audit_entry() {
        // given
        var auditEntry = TestAuditData.randomAuditEntry();

        // when
        sut.save(auditEntry);

        // then
        var saved = queryRepository.findById(auditEntry.id());

        assertThat(saved.id()).isEqualTo(auditEntry.id());
        assertThat(saved.type()).isEqualTo(auditEntry.type());
        assertThat(saved.target()).isEqualTo(auditEntry.target());
        assertThat(saved.context()).isEqualTo(auditEntry.context());
        assertThat(saved.changes()).isEqualTo(auditEntry.changes());
        assertThat(saved.actor()).isEqualTo(auditEntry.actor());
        assertThat(saved.time()).isEqualTo(auditEntry.time());
    }

}
