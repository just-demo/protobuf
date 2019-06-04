package demo.proto;

import com.google.protobuf.BytesValue;
import demo.proto.types.Types;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TryTypesTest {
    @Test
    public void testTypes() {
        Types types = Types.newBuilder()
                .build();
        assertEquals("", types.getString());
        assertEquals("", types.getStringValue().getValue());
        assertFalse(types.hasStringValue());
        assertEquals(BytesValue.getDefaultInstance().getValue(), types.getBytes());
        assertFalse(types.hasBytesValue());
    }
}
