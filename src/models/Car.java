package models;

public class Car {

    private String brand;
    private String model;
    private String registrationNumber;
    private String registrationDate;
    private int kmDriven;

    public Car(String registrationNumber, String brand, String model, String registrationDate, int kmDriven) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.registrationDate = registrationDate;
        this.kmDriven = kmDriven;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationDate='" + registrationDate + '\'' +
                ", kmDriven=" + kmDriven +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(int kmDriven) {
        this.kmDriven = kmDriven;
    }
}
