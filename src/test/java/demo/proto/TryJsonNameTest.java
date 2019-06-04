package demo.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryJsonNameTest {
    @Test
    public void testJsonName() throws InvalidProtocolBufferException {
        TryJsonName.JsonNameMessage message = TryJsonName.JsonNameMessage.newBuilder()
                .setId(1)
                .build();

        assertEquals("{\"json_id\":1}", JsonFormat.printer().omittingInsignificantWhitespace().print(message));
    }
}
