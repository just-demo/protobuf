package demo.mapstruct;

public class UserB {
    private AddressB address;

    public UserB() {

    }

    public UserB(AddressB address) {
        this.address = address;
    }

    public AddressB getAddress() {
        return address;
    }

    public void setAddress(AddressB address) {
        this.address = address;
    }
}
