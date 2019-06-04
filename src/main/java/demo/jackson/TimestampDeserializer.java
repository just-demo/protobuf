package demo.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.protobuf.Timestamp;
import com.google.protobuf.util.Timestamps;

import java.io.IOException;

import static com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT;

public class TimestampDeserializer extends StdDeserializer<Timestamp> {
    TimestampDeserializer() {
        super(Timestamp.class);
    }

    @Override
    public Timestamp deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        switch (parser.getCurrentToken()) {
            case VALUE_NUMBER_INT: return Timestamps.fromMillis(parser.getLongValue());
            default: // throw an error
                throw new IllegalArgumentException("Say no to " + context.getParser().currentToken());
        }
    }
}
