package models;

import models.Car;

public class Family extends Car {

    private boolean manualGear;
    private boolean airCondition;
    private boolean cruiseControl;
    private boolean sevenSeatsOrMore;


    public Family(String registrationNumber, String brand, String model, String registrationDate, int kmDriven, boolean manualGear,
                  boolean airCondition, boolean cruiseControl, boolean sevenSeatsOrMore) {
        super(registrationNumber, brand, model, registrationDate, kmDriven);
        this.manualGear = manualGear;
        this.airCondition = airCondition;
        this.cruiseControl = cruiseControl;
        this.sevenSeatsOrMore = sevenSeatsOrMore;
    }

    @Override
    public String toString() {
        return /*getClass().getSimpleName()+" car: "+super.toString()+*/
                String.format("| %-14s %-14s %-12s %-12s %-12d %-10b %-10b %-13b %-13b |",
                        getRegistrationNumber(), getBrand(), getModel(), getRegistrationDate(), getKmDriven(),
                        isManualGear(),isAirCondition(), isCruiseControl(), isSevenSeatsOrMore());


    }

    public boolean isManualGear() {
        return manualGear;
    }

    public void setManualGear(boolean manualGear) {
        this.manualGear = manualGear;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public boolean isCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public boolean isSevenSeatsOrMore() {
        return sevenSeatsOrMore;
    }

    public void setSevenSeatsOrMore(boolean sevenSeatsOrMore) {
        this.sevenSeatsOrMore = sevenSeatsOrMore;
    }
}
