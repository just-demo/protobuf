package demo;

public class UserMapperMain {
    public static void main(String[] args) {
demo.pojo.User pojo = new demo.pojo.User(123, "demo");
demo.proto.User proto = UserMapper.INSTANCE.map(pojo);

demo.proto.User proto2 = demo.proto.User.newBuilder()
        .setId(123)
        .setName("demo")
        .build();
demo.pojo.User pojo2 = UserMapper.INSTANCE.map(proto2);

        System.out.println(pojo2);
    }
}
