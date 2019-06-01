package self.ed.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;
import self.ed.proto.alias.Demo;

import static org.junit.Assert.*;
import static self.ed.proto.alias.Demo.Type.ONE;
import static self.ed.proto.alias.Demo.Type.TWO;

public class TryAliasTest {
    @Test
    public void testAlias() throws InvalidProtocolBufferException {
        assertSame(ONE, TWO);

        Demo demo = Demo.newBuilder().build();
        assertEquals(ONE, demo.getType());

        demo = Demo.newBuilder().setType(TWO).build();
        assertEquals(TWO, demo.getType());

        Demo.Builder demoBuilder = Demo.newBuilder();
        JsonFormat.parser().merge("{\"type\":\"ONE\"}", demoBuilder);
        assertEquals(ONE, demoBuilder.getType());

        demoBuilder = Demo.newBuilder();
        JsonFormat.parser().merge("{\"type\":\"TWO\"}", demoBuilder);
        assertEquals(ONE, demoBuilder.getType());
    }
}
