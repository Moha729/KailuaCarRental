package service;

import UI.UITools;
import models.Car;
import repository.CarRepository;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

public class CarService {

    CarRepository carRepository;

    public CarService() {

        carRepository = new CarRepository();
    }

    public void populateCars(ArrayList<Car> carList) {
        carRepository.populateCars(carList);
    }

    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) throws SQLException {
        carRepository.createCar(statement, userInput, carList, tools);
    }

    public void viewCars(ArrayList<Car> carList, UITools tools) {
        carRepository.viewCars(carList, tools);
    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) throws SQLException {
        carRepository.updateCar(statement, userInput, carList, tools);
    }

    public void delete(ArrayList<Car> carList, UITools tools) throws SQLException {
        carRepository.delete(carList, tools);
    }
}