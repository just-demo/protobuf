package self.ed.proto;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static self.ed.proto.TryDefault.DefaultMessage.Type.B;

public class TryDefaultTest {
    @Test
    public void testDefault() {
        TryDefault.DefaultMessage.Builder builder = TryDefault.DefaultMessage.newBuilder();
        assertEquals(123, builder.getId());
        assertEquals(B, builder.getType());
    }
}
