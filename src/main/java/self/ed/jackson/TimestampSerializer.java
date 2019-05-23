package self.ed.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;

import java.io.IOException;

public class TimestampSerializer extends StdSerializer<Timestamp> {
    TimestampSerializer() {
        super(Timestamp.class);
    }

    @Override
    public void serialize(Timestamp timestamp, JsonGenerator generator, SerializerProvider provider) throws IOException {
        generator.writeNumber(Timestamps.toMillis(timestamp));
    }
}
