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

    public Luxury createLuxury(String registrationNumber, String brand, String model, String registrationDate, int kmDriven, UITools tools) throws SQLException {
       return luxuryRepository.createLuxury(registrationNumber, brand, model, registrationDate, kmDriven, tools);
    }

    public void populateLuxuryToArrayList(ArrayList<Car> carList) {
        luxuryRepository.populateLuxuryToArrayList(carList);
    }


    public void viewLuxuryCars(ArrayList<Car> carList, UITools tools) {
        luxuryRepository.viewLuxuryCars(carList, tools);
    }

    public void updateLuxuryCar(Scanner userInput, Luxury car, UITools tools){

        luxuryRepository.updateLuxuryCar(userInput, car, tools);
    }
    public void deleteLuxuryCar(ArrayList<Car> carList, UITools tools, Car car){
        luxuryRepository.deleteLuxuryCar(carList, tools,car);
    }
}


