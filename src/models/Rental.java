package models;

import models.Car;
import models.Customer;

public class Rental {


    private String fromDateAndTime;
    private String toDateAndTime;
    private int maxKm;
    private Car car;
    private Customer customer;

    public Rental(String fromDateAndTime, String toDateAndTime, int maxKm, Car car, Customer customer) {
        this.fromDateAndTime = fromDateAndTime;
        this.toDateAndTime = toDateAndTime;
        this.maxKm = maxKm;
        this.car = car;
        this.customer = customer;
    }

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
