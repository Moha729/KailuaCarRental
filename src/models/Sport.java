package models;

import models.Car;

public class Sport extends Car {
    private boolean manualGear;
    private boolean over200HP;

    public Sport(String brand, String model, String registrationNumber, String registrationDate,
                 int kmDriven, boolean manualGear, boolean over200HP) {
        super(brand, model, registrationNumber, registrationDate, kmDriven);
        this.manualGear = manualGear;
        this.over200HP = over200HP;
    }

    @Override
    public String toString() {
        return "models.Sport{" +
                "manualGear=" + manualGear +
                ", over200HP=" + over200HP +
                '}';
    }

    public boolean isManualGear() {
        return manualGear;
    }

    public void setManualGear(boolean manualGear) {
        this.manualGear = manualGear;
    }

    public boolean isOver200HP() {
        return over200HP;
    }

    public void setOver200HP(boolean over200HP) {
        this.over200HP = over200HP;
    }
}
