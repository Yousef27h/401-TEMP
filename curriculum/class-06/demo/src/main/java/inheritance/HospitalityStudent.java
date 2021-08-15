package inheritance;

public class HospitalityStudent extends Student {

    private boolean hasApron;

    public HospitalityStudent(String name, String number) {
        super(name, number);
    }

    public boolean isHasApron() {
        return hasApron;
    }

    public void setHasApron(boolean hasApron) {
        this.hasApron = hasApron;
    }
}
