package demo.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.protobuf.Timestamp;

public class TimestampModule extends SimpleModule {
    public TimestampModule() {
        addSerializer(Timestamp.class, new TimestampSerializer());
        addDeserializer(Timestamp.class, new TimestampDeserializer());
    }
}
