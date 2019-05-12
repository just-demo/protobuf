package self.ed.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import com.google.protobuf.util.JsonFormat.Parser;
import com.google.protobuf.util.JsonFormat.Printer;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper POJO_MAPPER = new ObjectMapper();
    private static final Printer PROTO_PRINTER = JsonFormat.printer();
    private static final Parser PROTO_PARSER = JsonFormat.parser();

    public static <T> T jsonToPojo(String json, Class<T> clazz) throws IOException {
        return pojoMapper().readValue(json, clazz);
    }

    public static <T> T jsonBytesToPojo(byte[] json, Class<T> clazz) throws IOException {
        return pojoMapper().readValue(json, clazz);
    }

    public static String pojoToJson(Object object) throws IOException {
        return pojoMapper().writeValueAsString(object);
    }

    public static byte[] pojoToJsonBytes(Object object) throws IOException {
        return pojoMapper().writeValueAsBytes(object);
    }

    public static <T extends GeneratedMessageV3.Builder<?>> T jsonToProto(String json, T builder) throws IOException {
        protoParser().merge(json, builder);
        return builder;
    }

    public static String protoToJson(MessageOrBuilder message) throws IOException {
        return protoPrinter().print(message);
    }

    private static ObjectMapper pojoMapper() {
        return POJO_MAPPER;
    }

    private static Printer protoPrinter() {
        return PROTO_PRINTER;
    }

    private static Parser protoParser() {
        return PROTO_PARSER;
    }
}
