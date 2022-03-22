package service;

import UI.UITools;
import models.Car;
import models.Sport;
import repository.SportRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SportService {

    SportRepository sportRepository;

    public SportService() {
        sportRepository = new SportRepository();
    }

    public void populateSportToArrayList( ArrayList<Car> carList) {
        sportRepository.populateSportToArrayList(carList);
    }

    public Sport createSportsCar(Statement statement,String registrationNumber, String brand, String model, String registrationDate, int kmDriven) throws SQLException {

        return sportRepository.createSportsCar(statement, registrationNumber, brand, model, registrationDate, kmDriven);
    }

    public void viewSportCars(ArrayList<Car> carList, UITools tools) {
        sportRepository.viewSportCars(carList, tools);
    }


    public void updateSportCar(Statement statement, Scanner userInput, String regNum, Sport car) {
        sportRepository.updateSportCar(statement, userInput, regNum, car);
    }
    public void deleteSportCar(ArrayList<Car> carList, UITools tools, Car car){
        sportRepository.deleteSportCar(carList, tools, car);
    }
}
