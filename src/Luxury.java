public class Luxury {
    private boolean over3000CCM;
    private boolean automaticGear;
    private boolean cruiseControl;
    private boolean leatherSeats;

    public Luxury(boolean over3000CCM, boolean automaticGear, boolean cruiseControl, boolean leatherSeats) {
        this.over3000CCM = over3000CCM;
        this.automaticGear = automaticGear;
        this.cruiseControl = cruiseControl;
        this.leatherSeats = leatherSeats;
    }

    @Override
    public String toString() {
        return "Luxury{" +
                "over3000CCM=" + over3000CCM +
                ", automaticGear=" + automaticGear +
                ", cruiseControl=" + cruiseControl +
                ", leatherSeats=" + leatherSeats +
                '}';
    }

    public boolean isOver3000CCM() {
        return over3000CCM;
    }

    public void setOver3000CCM(boolean over3000CCM) {
        this.over3000CCM = over3000CCM;
    }

    public boolean isAutomaticGear() {
        return automaticGear;
    }

    public void setAutomaticGear(boolean automaticGear) {
        this.automaticGear = automaticGear;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isLeatherSeats() {
        return leatherSeats;
    }

    public void setLeatherSeats(boolean leatherSeats) {
        this.leatherSeats = leatherSeats;
    }
}