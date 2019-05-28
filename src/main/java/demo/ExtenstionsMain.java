package demo;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import self.ed.proto.ext.UserOuterClass;
import self.ed.proto.ext.User;

public class ExtenstionsMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {
User user = User.newBuilder()
        .setId(123)
        .setExtension(UserOuterClass.name, "demo")
        .build();
System.out.println(JsonFormat.printer().omittingInsignificantWhitespace().print(user));
    }
}
