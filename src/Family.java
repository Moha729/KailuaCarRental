public class Family extends Car{

    private boolean manualGear;
    private boolean airCondition;
    private boolean cruiseControl;
    private boolean sevenSeatsOrMore;


    public Family(String brand, String model, String registrationNumber, String registrationDate, int kmDriven, boolean manualGear, boolean airCondition, boolean cruiseControl, boolean sevenSeatsOrMore) {
        super(brand, model, registrationNumber, registrationDate, kmDriven);
        this.manualGear = manualGear;
        this.airCondition = airCondition;
        this.cruiseControl = cruiseControl;
        this.sevenSeatsOrMore = sevenSeatsOrMore;
    }

    @Override
    public String toString() {
        return "Family{" +
                "manualGear=" + manualGear +
                ", airCondition=" + airCondition +
                ", cruiseControl=" + cruiseControl +
                ", sevenSeatsOrMore=" + sevenSeatsOrMore +
                '}';
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
