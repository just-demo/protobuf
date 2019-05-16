package self.ed.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.util.JsonFormat;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;

public class JsonUtils {
    public static final ObjectMapper POJO_MAPPER = new ObjectMapper();
    public static final ObjectMapper PROTO_MAPPER = new ObjectMapper().registerModule(new ProtobufModule());
    public static final JsonFormat.Printer PROTO_PRINTER = JsonFormat.printer();
    public static final JsonFormat.Parser PROTO_PARSER = JsonFormat.parser();
}
