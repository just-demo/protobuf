package demo.proto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.Timestamps;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import demo.jackson.TimestampModule;
import demo.proto.time.TimestampMessage;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TryTimestampTest {
    @Test
    @SuppressWarnings("unused")
    public void testTimestampFormat() throws Exception {
        ObjectMapper customMapper = new ObjectMapper().registerModules(
                new ProtobufModule(),
                new TimestampModule()
        );

        Date date = new Date();

        Object pojo = new Object() {
            public Date timestamp = date;
        };

        TimestampMessage proto = TimestampMessage.newBuilder()
                .setTimestamp(Timestamps.fromMillis(date.getTime()))
                .build();

        String jsonPojo = new ObjectMapper().writeValueAsString(pojo);
        String jsonProtoDefault = JsonFormat.printer().omittingInsignificantWhitespace().print(proto);
        String jsonProtoCustom = customMapper.writeValueAsString(proto);
        TimestampMessage proto2 = customMapper.readValue(jsonProtoCustom, TimestampMessage.class);

        assertEquals("{\"timestamp\":" + date.getTime() + "}", jsonPojo);
        assertNotEquals(jsonPojo, jsonProtoDefault);
        assertEquals(jsonPojo, jsonProtoCustom);
        assertEquals(proto, proto2);
    }
}
