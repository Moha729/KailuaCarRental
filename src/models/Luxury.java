package models;



public class Luxury extends Car {

    private boolean over3000CCM;
    private boolean automaticGear;
    private boolean cruiseControl;
    private boolean leatherSeats;

    public Luxury(String brand, String model, String registrationNumber, String registrationDate, int kmDriven,
                  boolean over3000CCM, boolean automaticGear, boolean cruiseControl, boolean leatherSeats) {
        super(brand, model, registrationNumber, registrationDate, kmDriven);
        this.over3000CCM = over3000CCM;
        this.automaticGear = automaticGear;
        this.cruiseControl = cruiseControl;
        this.leatherSeats = leatherSeats;
    }

    @Override
    public String toString() {
        return "models.Luxury{" +
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
