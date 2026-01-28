package pl.feature.toggle.service.audit.infrastructure.out.db.audit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.feature.toggle.service.audit.AbstractITTest;
import pl.feature.toggle.service.audit.application.port.out.AuditRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.feature.toggle.service.audit.domain.FakeAuditEntryBuilder.fakeAuditEntryBuilder;

class AuditRepositoryIT extends AbstractITTest {

    @Autowired
    private AuditRepository sut;

    @Test
    @DisplayName("Should save audit and find by id")
    void test01() {
        // given
        var auditEntry = fakeAuditEntryBuilder().build();

        // when
        sut.save(auditEntry);

        // then
        var actual = sut.findById(auditEntry.id());
        assertThat(actual.actor()).isEqualTo(auditEntry.actor());
        assertThat(actual.target()).isEqualTo(auditEntry.target());
        assertThat(actual.context()).isEqualTo(auditEntry.context());
        assertThat(actual.type()).isEqualTo(auditEntry.type());
        assertThat(actual.time()).isEqualTo(auditEntry.time());
        assertThat(actual.changes()).isEqualTo(auditEntry.changes());
        assertThat(actual.id()).isEqualTo(auditEntry.id());
    }


}
