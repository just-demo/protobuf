package demo;

import com.google.protobuf.InvalidProtocolBufferException;
import demo.proto.User;

public class ProtoMain {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        User user = User.newBuilder()
                .setId(123)
                .setName("demo")
                .build();
        byte[] data = user.toByteArray();
        send(data);
        byte[] data2 = receive();
        User user2 = User.parseFrom(data);
    }

    private static void send(byte[] obj) {

    }

    private static byte[] receive() {
        return null;
    }

}
