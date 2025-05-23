package test.com.brinvex.brokercon.adapter.amnd;

import com.brinvex.brokercon.adapter.amnd.api.AmndModule;
import com.brinvex.brokercon.core.api.domain.Account;
import com.brinvex.brokercon.testsupport.TestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

abstract class AmndBaseTest {

    protected final Logger LOG = LoggerFactory.getLogger(getClass());

    protected final TestContext testCtx = new TestContext(AmndModule.class);

    protected final Account account1 = Account.of(testCtx.subProperties("account1"));

    protected final Account account2 = Account.of(testCtx.subProperties("account2"));

    protected final Account account3 = Account.of(testCtx.subProperties("account3"));

    public boolean account1IsNotNull() {
        return account1 != null;
    }

    public boolean account2IsNotNull() {
        return account2 != null;
    }

    public boolean account3IsNotNull() {
        return account3 != null;
    }
}
