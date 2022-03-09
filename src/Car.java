public class Car {

    private String brand;
    private String model;
    private String registrationNumber;
    private String registrationDate;
    private int kmDriven;

    public Car(String brand, String model, String registrationNumber, String registrationDate, int kmDriven) {
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.registrationDate = registrationDate;
        this.kmDriven = kmDriven;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
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
