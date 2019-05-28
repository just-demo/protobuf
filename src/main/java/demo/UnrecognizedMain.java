package demo;


import self.ed.proto.unreq.User;

public class UnrecognizedMain {
    public static void main(String[] args) {
        User.newBuilder().build().getStatus(); // UNSPECIFIED
        //User.newBuilder().setStatus(Status.UNRECOGNIZED); // error thrown
        User.newBuilder().setStatusValue(-1).build().getStatus(); // UNRECOGNIZED
        User.newBuilder().setStatusValue(3).build().getStatus(); // UNRECOGNIZED
        System.out.println(User.newBuilder().setStatusValue(3).build().getStatus());
    }
}