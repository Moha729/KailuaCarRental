package repository;

import UI.UITools;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;
import service.FamilyService;
import service.LuxuryService;
import service.SportService;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class CarRepository {
    UITools menuTools = new UITools();
    LuxuryService luxuryService = new LuxuryService();
    SportService sportService = new SportService();
    FamilyService familyService = new FamilyService();

    public void populateCars(ArrayList<Car> carList) {
        luxuryService.populateLuxuryToArrayList(carList);
        familyService.populateFamilyToArrayList(carList);
        sportService.populateSportToArrayList(carList);
    }

    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) {

        printHeadline("New car");

        tools.customizedButton(120, 1, "What is the type of the new car?");

        System.out.print(tools.doubleButton(">1< Luxury", ">2< Sport"));
        System.out.print(tools.doubleButton(">3< Family", ">4< Back"));

        int chooseType = userInput.nextInt();

        if (chooseType == 0 || chooseType == 4) {
            return;
        }

        tools.customizedButton(120, 1, "Register car");
        System.out.println();
        String registrationNumber = tools.returnStringInfo(60, 1, "Enter registration number");

        String brand = tools.returnStringInfo(60, 1, "Enter brand");

        String model = tools.returnStringInfo(60, 1, "Enter model");

        String registrationDate = tools.returnStringInfo(60, 1, "Enter date");

        int kmDriven = tools.returnIntInfo(60, 1, "Enter km driven");

        try {
            if (chooseType == 1) {

                Luxury luxuryCar = luxuryService.createLuxury(statement, registrationNumber, brand, model,
                        registrationDate, kmDriven, tools);
                carList.add(luxuryCar);

            } else if (chooseType == 2) {

                Sport sportsCar = sportService.createSportsCar(statement, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(sportsCar);

            } else if (chooseType == 3) {

                Family familyCar = familyService.createFamilyCar(statement, registrationNumber, brand, model,
                        registrationDate, kmDriven, tools);
                carList.add(familyCar);

            } else {
                tools.customizedButton(60, 1, "Try again!");
            }
        } catch (SQLException sqlExc) {
            System.out.println("Error in creating car " + sqlExc);
            System.exit(1);
        }
    }

    public void viewCars(ArrayList<Car> carList, UITools tools) {
        printHeadline("Cars");
        viewCarsS(carList, tools);
    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) throws SQLException {

        printHeadline("Update car");
        Car car = getCar(carList, tools);
        String regNum = car.getRegistrationNumber();

        if (car.getClass().getSimpleName().equalsIgnoreCase("luxury")) {
            luxuryService.updateLuxuryCar(statement, userInput, regNum, (Luxury) car);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("sport")) {
            Sport sportCar = (Sport) car;
            sportService.updateSportCar(statement, userInput, regNum, sportCar);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("family")) {
            Family familyCar = (Family) car;
            familyService.updateFamilyCar(statement, userInput, regNum, familyCar);
        }
        System.out.println();
        tools.customizedButton(40, 1, "Car is updated");
        System.out.println();
    }

    public void delete(ArrayList<Car> carList, UITools tools) {

        printHeadline("Delete car");
        Car car = getCar(carList, tools);
        String answer = car.getRegistrationNumber();

        if (car.getClass().getSimpleName().equalsIgnoreCase("luxury")) {
            luxuryService.deleteLuxuryCar(carList, answer, tools);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Sport")) {
            sportService.deleteSportCar(carList, tools, car);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Family")) {
            familyService.deleteFamilyCar(carList, answer, tools);
        }
    }

    private void viewCarsS(ArrayList<Car> carList, UITools tools) {
        luxuryService.viewLuxuryCars(carList, tools);
        familyService.viewFamilyCars(carList, tools);
        sportService.viewSportCars(carList, tools);
    }

    private void printHeadline(String text) {
        menuTools.whiteSpace();
        menuTools.whiteSpace();
        menuTools.customizedButton(120, 3, text);
        System.out.println();
    }

    public Car getCar(ArrayList<Car> carList, String regNum) {//Overload example
        Car car = null;
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNum)) {
                car = carList.get(i);
            }
        }
        return car;
    }

    public Car getCar(ArrayList<Car> carList, UITools tools) {//Overload example
        viewCarsS(carList, tools);
        String regNum = tools.returnStringInfo(50, 1, "Enter registration number");
        Car car = null;
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNum)) {
                car = carList.get(i);
            }
        }
        return car;
    }
}