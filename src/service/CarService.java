package service;

import UI.MoTools;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;
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

    public void populateCars(Statement statement, ArrayList<Car> carList) {
        carRepository.populateCars(statement, carList);
    }


    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList, MoTools tools) throws SQLException {
        carRepository.createCar(statement, userInput, carList, tools);
    }


    public void viewCars(ArrayList<Car> carList, MoTools tools) {
        carRepository.viewCars(carList, tools);
    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {
        carRepository.updateCar(statement, userInput, carList);
    }

    public void delete(Statement statement, ArrayList<Car> carList, Scanner userInput, MoTools tools) throws SQLException {
        carRepository.delete(statement, carList, userInput, tools);
    }
}