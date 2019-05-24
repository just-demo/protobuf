package self.ed.proto;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TryExtensionTest {
    @Test
    public void testExtension() throws InvalidProtocolBufferException {
        TryExtension.ExtensionsMessage message = TryExtension.ExtensionsMessage.newBuilder()
                .setId(1)
                .setExtension(TryExtension.name, "Demo name")
                .build();

        assertEquals("{\"id\":1,\"name\":\"Demo name\"}", JsonFormat.printer().omittingInsignificantWhitespace().print(message));
    }
}
