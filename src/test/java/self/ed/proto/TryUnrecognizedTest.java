package self.ed.proto;

import com.google.protobuf.util.JsonFormat;
import org.junit.Test;
import self.ed.proto.TryUnrecognized.UnrecognizedMessage;

import static org.junit.Assert.assertEquals;
import static self.ed.proto.TryUnrecognized.UnrecognizedMessage.Type.A;
import static self.ed.proto.TryUnrecognized.UnrecognizedMessage.Type.UNRECOGNIZED;

public class TryUnrecognizedTest {
    @Test
    public void testUnrecognized() {
        UnrecognizedMessage.Builder builder = UnrecognizedMessage.newBuilder()
                .setTypeValue(1000);

        assertEquals(UNRECOGNIZED, builder.getType());
        assertEquals(1000, builder.getTypeValue());

        UnrecognizedMessage message = builder.build();

        assertEquals(UNRECOGNIZED, message.getType());
        assertEquals(1000, message.getTypeValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnrecognized_CannotSetEnumExplicitly() {
        UnrecognizedMessage.newBuilder().setType(UNRECOGNIZED);
    }

    @Test
    public void testUnrecognized_CanSetEnumValueExplicitly() {
        UnrecognizedMessage message = UnrecognizedMessage.newBuilder()
                .setTypeValue(-1)
                .build();

        assertEquals(UNRECOGNIZED, message.getType());
        assertEquals(-1, message.getTypeValue());
    }

    @Test
    public void testUnrecognized_Deserialization() throws Exception {
        UnrecognizedMessage input = UnrecognizedMessage.newBuilder()
                .setTypeValue(1000)
                .build();

        UnrecognizedMessage output = UnrecognizedMessage.parseFrom(input.toByteArray());

        assertEquals(UNRECOGNIZED, output.getType());
        assertEquals(1000, output.getTypeValue());
    }

    @Test
    public void testUnrecognized_DefaultIsZeroElement() {
        UnrecognizedMessage.Builder builder = UnrecognizedMessage.newBuilder();

        assertEquals(A, builder.getType());
        assertEquals(0, builder.getTypeValue());
    }

    @Test
    public void testUnrecognized_JsonIncludesNumericValue() throws Exception {
        UnrecognizedMessage message = UnrecognizedMessage.newBuilder()
                .setTypeValue(1000)
                .build();

        assertEquals("{\"type\":1000}", JsonFormat.printer().omittingInsignificantWhitespace().print(message));
    }
}
