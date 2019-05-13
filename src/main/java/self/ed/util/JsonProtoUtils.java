package self.ed.util;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;

import java.io.IOException;

public class JsonProtoUtils {
    private static final JsonFormat.Printer PROTO_PRINTER = JsonFormat.printer();
    private static final JsonFormat.Parser PROTO_PARSER = JsonFormat.parser();

    public static String protoToJson(MessageOrBuilder message) throws IOException {
        return PROTO_PRINTER.print(message);
    }

    public static <T extends GeneratedMessageV3.Builder<?>> T jsonToProto(String json, T builder) throws IOException {
        PROTO_PARSER.merge(json, builder);
        return builder;
    }
}
