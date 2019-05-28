package demo.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import demo.proto.User;

public class JacksonUtils {
static ObjectMapper mapper = new ObjectMapper().registerModule(new ProtobufModule());
    public static void main(String[] args) throws Exception {
        main1(null);
        main2(null);
    }

    public static void main1(String[] args) throws Exception {
// proto to JSON
User user = User.newBuilder()
        .setId(123)
        .setName("demo")
        .build();
String json = mapper.writeValueAsString(user);
        System.out.println(json);
    }

    public static void main2(String[] args) throws Exception {
// JSON to proto
String json = "{\"id\":123,\"name\":\"demo\"}";
User user = mapper.readValue(json, User.class);
        System.out.println(user);
    }
}
