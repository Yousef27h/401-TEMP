package inheritance;

public class ASACStudent extends Student {
    private boolean hasPC;

    public ASACStudent(String name, String number, boolean hasPC) {
        super(name, number);
        this.hasPC = hasPC;
    }

    public ASACStudent(String name, String number, boolean hasPC, String address) {
        super(name, number, address);
        this.hasPC = hasPC;
    }

    public boolean isHasPC() {
        return hasPC;
    }

    public void setHasPC(boolean hasPC) {
        this.hasPC = hasPC;
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(super.getAddress() + " 123 mansion ave");
    }
}
