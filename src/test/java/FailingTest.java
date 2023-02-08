import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class FailingTest {

    @Test
    @Disabled
    public void willFail() {
        fail();
    }
}
