package service;

import UI.Buttons;
import models.Car;
import models.Family;
import repository.FamilyRepository;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class FamilyService {

    FamilyRepository familyRepository;

    public FamilyService() {
        familyRepository = new FamilyRepository();
    }

    public Family createFamilyCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String registrationNumber, String brand, String model, String registrationDate, int kmDriven, Buttons tools) {
        return familyRepository.createFamilyCar(statement,userInput,carList,registrationNumber,brand,model,registrationDate,kmDriven,tools);
    }


    public void viewFamilyCars(ArrayList<Car> carList, Buttons tools) {
        familyRepository.viewFamilyCars(carList,tools);
    }


    public void updateFamilyCar(Statement statement, ArrayList<Car> carList, Scanner userInput, String regNum, Car car) {
        familyRepository.updateFamilyCar(statement,carList,userInput, regNum, car);
    }


    public void populateFamilyToArrayList(Statement statement, ArrayList<Car> carList) {
        familyRepository.populateFamilyToArrayList(statement,carList);
    }
}
