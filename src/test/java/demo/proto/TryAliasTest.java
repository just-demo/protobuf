package demo.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import demo.proto.alias.Demo;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TryAliasTest {
    @Test
    public void testAlias() throws InvalidProtocolBufferException {
        assertSame(Demo.Type.ONE, Demo.Type.TWO);

        Demo demo = Demo.newBuilder().build();
        Assert.assertEquals(Demo.Type.ONE, demo.getType());

        demo = Demo.newBuilder().setType(Demo.Type.TWO).build();
        Assert.assertEquals(Demo.Type.TWO, demo.getType());

        Demo.Builder demoBuilder = Demo.newBuilder();
        JsonFormat.parser().merge("{\"type\":\"ONE\"}", demoBuilder);
        Assert.assertEquals(Demo.Type.ONE, demoBuilder.getType());

        demoBuilder = Demo.newBuilder();
        JsonFormat.parser().merge("{\"type\":\"TWO\"}", demoBuilder);
        Assert.assertEquals(Demo.Type.ONE, demoBuilder.getType());
    }
}
