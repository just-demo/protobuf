package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import com.just.demo.DemoCamel;
import com.just.demo.User;
import com.just.demo.Users;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class UserMapperMain {
    public static void main(String[] args) throws InvalidProtocolBufferException, JsonProcessingException {
        demo.pojo.User pojo = new demo.pojo.User(123, "demo");
        demo.proto.User proto = UserMapper.INSTANCE.map(pojo);

        demo.proto.User proto2 = demo.proto.User.newBuilder()
                .setId(123)
                .setName("demo")
                .build();
        demo.pojo.User pojo2 = UserMapper.INSTANCE.map(proto2);

        String json = "{\"id\":123,\"name\":\"demo\"}";

        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(proto));
        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(
                DemoCamel.newBuilder().setForgotAboutUnderscore("demo")));

        List<User> users = asList(User.newBuilder().build());
        Users wrapper = Users.newBuilder().addAllUsers(users).build();
        wrapper.toByteArray();
        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(wrapper));
        System.out.println(new ObjectMapper().registerModule(new ProtobufModule()).writeValueAsString(users));
    }
}
