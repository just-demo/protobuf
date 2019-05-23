package self.ed.proto;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;
import self.ed.proto.any.AnyOne;
import self.ed.proto.any.AnyTwo;
import self.ed.proto.any.Demo;

import static org.junit.Assert.assertEquals;

public class TryAnyTest {
    @Test
    public void testDefault() throws InvalidProtocolBufferException {
        AnyOne anyOne = AnyOne.newBuilder()
                .setName("demo")
                .build();
        AnyTwo anyTwo = AnyTwo.newBuilder()
                .setCount(123)
                .build();

        Demo demoOne = Demo.newBuilder()
                .setAny(Any.pack(anyOne))
                .build();

        Demo demoTwo = Demo.newBuilder()
                .setAny(Any.pack(anyTwo))
                .build();

        AnyOne anyOne2 = Demo.parseFrom(demoOne.toByteArray()).getAny().unpack(AnyOne.class);
        AnyTwo anyTwo2 = Demo.parseFrom(demoTwo.toByteArray()).getAny().unpack(AnyTwo.class);

        assertEquals("demo", anyOne2.getName());
        assertEquals(123, anyTwo2.getCount());
    }
}
