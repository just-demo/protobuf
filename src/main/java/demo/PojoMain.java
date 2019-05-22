package demo;

public class PojoMain {
    public static void main(String[] args) {
        User user = new User(123, "demo");
        String data = toJson(user);
        send(data);
        String data2 = receive();
        User user2 = fromJson(data);
    }

    private static void send(String obj) {

    }

    private static String receive() {
        return null;
    }

    private static String toJson(Object obj) {
        return null;
    }

    private static User fromJson(String str) {
        return null;
    }
}
