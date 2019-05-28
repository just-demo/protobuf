package demo.json;

import com.google.protobuf.util.JsonFormat;
import demo.proto.User;

public class ProtoJsonUtils {
    public static void main(String[] args) throws Exception {
        main1(null);
        main2(null);
    }

    public static void main1(String[] args) throws Exception {
        User user = User.newBuilder()
                .setId(123)
                .setName("demo")
                .build();
        String json = JsonFormat.printer().print(user);
        System.out.println(json);
    }

    public static void main2(String[] args) throws Exception {
        String json = "{\"id\":123,\"name\":\"demo\"}";
        User.Builder userBuilder = User.newBuilder();
        JsonFormat.parser().merge(json, userBuilder);
        User user = userBuilder.build();
        System.out.println(user);
    }
}
