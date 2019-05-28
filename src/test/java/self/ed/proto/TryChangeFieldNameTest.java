package self.ed.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;
import self.ed.proto.change.Abc;
import self.ed.proto.change.Def;

import static org.junit.Assert.assertEquals;

public class TryChangeFieldNameTest {
    @Test
    public void testNullable_Default() throws InvalidProtocolBufferException {
        Abc abc = Abc.newBuilder().setAbc(123).build();
        Def def = Def.parseFrom(abc.toByteArray());
        assertEquals(123L, def.getDef());
    }
}
