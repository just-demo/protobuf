package demo.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import demo.proto.change.Abc;
import demo.proto.change.Def;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryChangeFieldNameTest {
    @Test
    public void testNullable_Default() throws InvalidProtocolBufferException {
        Abc abc = Abc.newBuilder().setAbc(123).build();
        Def def = Def.parseFrom(abc.toByteArray());
        assertEquals(123L, def.getDef());
    }
}
