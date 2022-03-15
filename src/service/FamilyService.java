package service;

import UI.MoTools;
import models.Car;
import models.Family;
import models.Sport;
import repository.FamilyRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class FamilyService {

    FamilyRepository familyRepository;

    public FamilyService() {
        familyRepository = new FamilyRepository();
    }

    public Family createFamilyCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String registrationNumber, String brand, String model, String registrationDate, int kmDriven, MoTools tools) {
        return familyRepository.createFamilyCar(statement,userInput,carList,registrationNumber,brand,model,registrationDate,kmDriven,tools);
    }


    public void viewFamilyCars(ArrayList<Car> carList, MoTools tools) {
        familyRepository.viewFamilyCars(carList,tools);
    }


    public void updateFamilyCar(Statement statement, ArrayList<Car> carList, Scanner userInput) {
        familyRepository.updateFamilyCar(statement,carList,userInput);
    }


    public void populateFamilyToArrayList(Statement statement, ArrayList<Car> carList) {
        familyRepository.populateFamilyToArrayList(statement,carList);
    }
}
