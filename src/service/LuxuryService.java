package service;

import UI.UITools;
import models.Car;
import models.Luxury;
import repository.LuxuryRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryService {

    LuxuryRepository luxuryRepository;

    public LuxuryService() {
        luxuryRepository = new LuxuryRepository();
    }

    public Luxury createLuxury(Statement statement, ArrayList<Car> carList, Scanner userInput, String registrationNumber, String brand, String model, String registrationDate, int kmDriven, UITools tools) throws SQLException {
       return luxuryRepository.createLuxury(statement, carList, userInput, registrationNumber, brand, model, registrationDate, kmDriven, tools);
    }

    public void populateLuxuryToArrayList(Statement statement, ArrayList<Car> carList) {
        luxuryRepository.populateLuxuryToArrayList(statement, carList);
    }


    public void viewLuxuryCars(ArrayList<Car> carList, UITools tools) {
        luxuryRepository.viewLuxuryCars(carList, tools);
    }

    public void updateLuxuryCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum, Luxury car) throws SQLException {

        luxuryRepository.updateLuxuryCar(statement, carList, userInput, regNum, car);
    }
}
