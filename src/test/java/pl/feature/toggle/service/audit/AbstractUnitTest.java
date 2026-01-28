package pl.feature.toggle.service.audit;

import org.junit.jupiter.api.BeforeEach;
import pl.feature.toggle.service.audit.infrastructure.FakeAcknowledgment;
import pl.feature.toggle.service.audit.infrastructure.FakeAuditRepository;
import pl.feature.toggle.service.audit.infrastructure.FakeProcessedEventRepository;
import pl.feature.toggle.service.contracts.shared.Metadata;

import java.util.UUID;

public abstract class AbstractUnitTest {

    protected FakeProcessedEventRepository processedEventRepository;
    protected FakeAuditRepository auditRepository;
    protected FakeAcknowledgment acknowledgment;

    protected static final UUID PROJECT_ID = UUID.randomUUID();
    protected static final UUID ENVIRONMENT_ID = UUID.randomUUID();
    protected static final UUID FEATURE_TOGGLE_ID = UUID.randomUUID();
    protected static final String ACTOR_ID = UUID.randomUUID().toString();
    protected static final String CORRELATION_ID = UUID.randomUUID().toString();
    protected static final String USERNAME = "admin";
    protected static final Metadata METADATA = Metadata.create(ACTOR_ID, USERNAME, CORRELATION_ID);

    @BeforeEach
    void setUp() {
        this.processedEventRepository = new FakeProcessedEventRepository();
        this.auditRepository = new FakeAuditRepository();
        this.acknowledgment = new FakeAcknowledgment();
    }

}
