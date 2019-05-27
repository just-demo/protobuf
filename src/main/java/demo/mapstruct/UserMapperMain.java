package demo.mapstruct;

public class UserMapperMain {
    public static void main(String[] args) {
        UserA userA = new UserA(new AddressA("abc"));
        UserB userB = UserMapper.INSTANCE.map(userA);
        System.out.println(userB);
    }
}
