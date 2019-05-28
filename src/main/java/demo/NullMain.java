package demo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import demo.proto.User;

public class NullMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        User user = User.newBuilder()
                .setId(0)
                .setName("")
                .build();
        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(user));
        System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().includingDefaultValueFields().print(user));
    }
}
