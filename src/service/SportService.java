package service;

import UI.Buttons;
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

    public void populateSportToArrayList(Statement statement, ArrayList<Car> carList) {
        sportRepository.populateSportToArrayList(statement,carList);
    }


    public Sport createSportsCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String registrationNumber, String brand, String model, String registrationDate, int kmDriven) throws SQLException {

        return sportRepository.createSportsCar(statement,userInput,carList,registrationNumber,brand,model,registrationDate,kmDriven);
    }

    public void viewSportCars(ArrayList<Car> carList, Buttons tools) {
        sportRepository.viewSportCars(carList,tools);
    }


    public void updateSportCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum, Car car) {
        sportRepository.updateSportCar(statement,carList,userInput, regNum, car);
    }
}
