package models;


public class Rental {

    private Car car;
    private Customer customer;
    private int rental_id;
    private String fromDateAndTime;
    private String toDateAndTime;
    private int maxKm;


    public Rental(Car car, Customer customer, int rental_id, String fromDateAndTime,
                  String toDateAndTime, int maxKm) {
        this.car = car;
        this.customer = customer;
        this.rental_id = rental_id;
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
        this.maxKm = maxKm;
    }

    @Override
    public String toString() {
        return "Rental: " + rental_id  +
                "\nCar: " + car.getBrand() + " " + car.getModel() + " " + car.getRegistrationNumber() +
                "\nCustomer: " + customer.getName() + " " +  customer.getDriverLicenseNumber() +
                "\nFromDateAndTime:'" + fromDateAndTime +
                "\nToDateAndTime='" + toDateAndTime +
                "\nMaxKm=" + maxKm;
    }

    public int getRental_id() { return rental_id; }

    public void setRental_id(int rental_id) { this.rental_id = rental_id; }

    public String getFromDateAndTime() {
        return fromDateAndTime;
    }

    public void setFromDateAndTime(String fromDateAndTime) {
        this.fromDateAndTime = fromDateAndTime;
    }

    public String getToDateAndTime() {
        return toDateAndTime;
    }

    public void setToDateAndTime(String toDateAndTime) {
        this.toDateAndTime = toDateAndTime;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

}
