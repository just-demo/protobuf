package demo.mapstruct;

public class UserA {
    private AddressA address;

    public UserA() {

    }

    public UserA(AddressA address) {
        this.address = address;
    }

    public AddressA getAddress() {
        return address;
    }

    public void setAddress(AddressA address) {
        this.address = address;
    }
}
