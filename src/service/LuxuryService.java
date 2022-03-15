package service;

import UI.MoTools;
import models.Car;
import models.Luxury;
import repository.LuxuryRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class LuxuryService {


    LuxuryRepository luxuryRepository;

    public LuxuryService() {
        luxuryRepository = new LuxuryRepository();
    }

    public Luxury createLuxury(Statement statement, ArrayList<Car> carList, Scanner userInput, String registrationNumber, String brand, String model, String registrationDate, int kmDriven) throws SQLException {
       return luxuryRepository.createLuxury(statement,carList,userInput,registrationNumber,brand,model,registrationDate,kmDriven);
    }

    public void populateLuxuryToArrayList(Statement statement, ArrayList<Car> carList) {
        luxuryRepository.populateLuxuryToArrayList(statement,carList);
    }


    public void viewLuxuryCars(ArrayList<Car> carList, MoTools tools) {
        luxuryRepository.viewLuxuryCars(carList,tools);
    }


    public void updateLuxuryCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum, Car car) {

        luxuryRepository.updateLuxuryCar(statement,carList,userInput, regNum, car);
    }
}
