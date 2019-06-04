package demo.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Struct;
import com.google.protobuf.Value;
import com.google.protobuf.util.JsonFormat;
import demo.proto.struct.ValueDirectUsage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryStructTest {
    @Test
    public void testStruct() throws InvalidProtocolBufferException {
        Struct nestedStruct = Struct.newBuilder()
                .putFields("nestedKey", Value.newBuilder().setStringValue("nested value").build())
                .build();

        Struct struct = Struct.newBuilder()
                .putFields("key", Value.newBuilder().setStringValue("value").build())
                .putFields("nested", Value.newBuilder().setStructValue(nestedStruct).build())
                .build();

        assertEquals("{\"key\":\"value\",\"nested\":{\"nestedKey\":\"nested value\"}}", JsonFormat.printer().omittingInsignificantWhitespace().print(struct));
    }

    @Test
    public void testNotSameAsCustom() throws InvalidProtocolBufferException {
        Struct struct = Struct.newBuilder()
                .putFields("key", Value.newBuilder().setStringValue("value").build())
                .build();

        demo.proto.struct.Struct customStruct = demo.proto.struct.Struct.newBuilder()
                .putFields("key", demo.proto.struct.Value.newBuilder().setStringValue("value").build())
                .build();

        assertEquals("{\"key\":\"value\"}", JsonFormat.printer().omittingInsignificantWhitespace().print(struct));
        assertEquals("{\"fields\":{\"key\":{\"stringValue\":\"value\"}}}", JsonFormat.printer().omittingInsignificantWhitespace().print(customStruct));
    }

    @Test
    public void testValueDirectUsage() throws InvalidProtocolBufferException {
        ValueDirectUsage valueWrapper = ValueDirectUsage.newBuilder()
                .setValue(Value.newBuilder().setStringValue("abc").build())
                .build();

        assertEquals("{\"value\":\"abc\"}", JsonFormat.printer().omittingInsignificantWhitespace().print(valueWrapper));
    }
}
