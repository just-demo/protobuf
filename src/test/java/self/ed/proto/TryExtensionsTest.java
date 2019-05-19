package self.ed.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryExtensionsTest {
    @Test
    public void testExtension() throws InvalidProtocolBufferException {
        TryExtensions.ExtensionsMessage message = TryExtensions.ExtensionsMessage.newBuilder()
                .setId(1)
                .setExtension(TryExtensions.name, "Demo name")
                .build();

        assertEquals("{\"id\":1,\"name\":\"Demo name\"}", JsonFormat.printer().omittingInsignificantWhitespace().print(message));
    }
}
