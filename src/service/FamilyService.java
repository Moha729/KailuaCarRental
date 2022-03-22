package service;

import UI.UITools;
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

    public Family createFamilyCar(Statement statement, String registrationNumber, String brand, String model, String registrationDate, int kmDriven, UITools tools) {
        return familyRepository.createFamilyCar(statement, registrationNumber, brand, model, registrationDate, kmDriven, tools);
    }


    public void viewFamilyCars(ArrayList<Car> carList, UITools tools) {
        familyRepository.viewFamilyCars(carList, tools);
    }


    public void updateFamilyCar(Statement statement, Scanner userInput, String regNum, Family car) {
        familyRepository.updateFamilyCar(statement, userInput, regNum, car);
    }


    public void populateFamilyToArrayList( ArrayList<Car> carList) {
        familyRepository.populateFamilyToArrayList( carList);
    }
    public void deleteFamilyCar(ArrayList<Car> carList, String answer, UITools tools){
        familyRepository.deleteFamilyCar(carList, answer, tools);
    }
}
