package models;

import models.Car;
import models.Customer;

public class Rental {

    private Car car;
    private Customer customer;
    public int rental_id;
    private String fromDateAndTime;
    private String toDateAndTime;
    private int maxKm;


    public Rental(Car car, Customer customer, int rental_id, String fromDateAndTime, String toDateAndTime, int maxKm) {
        this.car = car;
        this.customer = customer;
        this.rental_id = rental_id;
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
        this.maxKm = maxKm;

    }

    public Rental(String registration_number, String brand, String model, String registration_date, int km_driven, String customer_driver_license_number, String customer_driver_since_number, String customer_first_name_, String customer_last_name, int customer_zip_code, String customer_city, int customer_phone_number, int customer_mobile_number, String customer_email, int rental_id, String rental_from_date, String rental_to_date, int rental_max_km) {
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

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
