package self.ed.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MessageOrBuilder;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;

import java.io.IOException;

public class JsonJacksonUtils {
    private static final ObjectMapper POJO_MAPPER = new ObjectMapper();
    private static final ObjectMapper PROTO_MAPPER = new ObjectMapper().registerModule(new ProtobufModule());

    public static String pojoToJson(Object object) throws IOException {
        return POJO_MAPPER.writeValueAsString(object);
    }

    public static <T> T jsonToPojo(String json, Class<T> clazz) throws IOException {
        return POJO_MAPPER.readValue(json, clazz);
    }

    public static byte[] pojoToJsonBytes(Object object) throws IOException {
        return POJO_MAPPER.writeValueAsBytes(object);
    }

    public static <T> T jsonBytesToPojo(byte[] json, Class<T> clazz) throws IOException {
        return POJO_MAPPER.readValue(json, clazz);
    }

    public static String protoToJson(MessageOrBuilder message) throws IOException {
        return PROTO_MAPPER.writeValueAsString(message);
    }

    public static <T extends GeneratedMessageV3> T jsonToProto(String json, Class<T> clazz) throws IOException {
        return PROTO_MAPPER.readValue(json, clazz);
    }
}
