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
    DBCarRepo dbCarRepo = new DBCarRepo();

    public void populateCars(ArrayList<Car> carList) {
        luxuryService.populateLuxuryToArrayList(carList);
        familyService.populateFamilyToArrayList(carList);
        sportService.populateSportToArrayList(carList);
    }

    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) {

        printHeadline("New car", tools);

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
        printHeadline("Cars", tools);
        viewCarsS(carList, tools);
    }


    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList, UITools tools) throws SQLException {

        printHeadline("Edit car", tools);

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


    public void delete(Statement statement, ArrayList<Car> carList, UITools tools) {

        printHeadline("Delete car", tools);
        Car car = getCar(carList, tools);
        String answer = car.getRegistrationNumber();

        if (car.getClass().getSimpleName().equalsIgnoreCase("luxury")) {

            luxuryService.deleteLuxuryCar(statement, carList, answer, tools);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Sport")) {

            sportService.deleteSportCar(statement, carList, answer, tools);
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Family")) {

            familyService.deleteFamilyCar(statement, carList, answer, tools);
        } else {
            System.out.println("Type in the right number");
        }
    }



    private void viewCarsS(ArrayList<Car> carList, UITools tools) {
        luxuryService.viewLuxuryCars(carList, tools);
        familyService.viewFamilyCars(carList, tools);
        sportService.viewSportCars(carList, tools);
    }

    private void printHeadline(String text, UITools tools) {
        menuTools.whiteSpace();
        menuTools.whiteSpace();
        tools.customizedButton(120, 3, text);
        System.out.println();
    }


    public Car getCar(ArrayList<Car> carList, String regNum) {//MÅ IKKE SLETTES
        Car car = null;
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNum)) {
                car = carList.get(i);
            }
        }
        return car;
    }

    public Car getCar(ArrayList<Car> carList, UITools tools) {//MÅ IKKE SLETTES
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
/*
                /*int updateIndex = 0;
        Family familyCar = null;
        Sport sportCar = null;
        Luxury luxuryCar = null;

        viewCars(carList, tools);
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNum)) {
                //System.out.println(carList.get(i));
                if (carList.get(i).getClass().getSimpleName().equals("Luxury")) {
                    updateIndex = 1;
                    luxuryCar = (Luxury) carList.get(i);
                    //System.out.println("It's a luxury");//statement to locate potential error
                } else if (carList.get(i).getClass().getSimpleName().equals("Sport")) {

                    //System.out.println("It's a sport");//statement to locate potential error
                } else if (carList.get(i).getClass().getSimpleName().equals("Family")) {

                    //System.out.println("It's a family");//statement to locate potential error
                }
            }
        }
       switch (updateIndex) {
            case 1 -> luxuryService.updateLuxuryCar(statement, userInput, regNum, luxuryCar);
            case 2 -> sportService.updateSportCar(statement, userInput, regNum, sportCar);
            case 3 -> familyService.updateFamilyCar(statement, userInput, regNum, familyCar);
        }*/
 /*       switch (deleteIndex) {
            case 1 -> dbCarRepo.deleteLuxuryCar(statement, carList, answer);
            case 2 -> dbCarRepo.deleteSportCar(statement, carList, answer);
            case 3 -> dbCarRepo.deleteFamilyCar(statement, carList, answer);
            default -> System.out.println("Type in the right number");
        }

        if (car.getClass().getSimpleName().equalsIgnoreCase("luxury")) {
            deleteIndex = 1;
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Sport")) {
            deleteIndex = 2;
        } else if (car.getClass().getSimpleName().equalsIgnoreCase("Family")) {
            deleteIndex = 3;
        }
 viewCars(carList, tools);
        System.out.println();
        String answer = tools.returnStringInfo(50, 1, "Enter registration number");
        String simpleName = getSimpleName(carList, answer);
        String carInfo = getCar(carList, answer).toString();
        switch (deleteIndex) {
            case 1 -> dbCarRepo.deleteLuxuryCar(statement, carList, answer);
            case 2 -> dbCarRepo.deleteSportCar(statement, carList, answer);
            case 3 -> dbCarRepo.deleteFamilyCar(statement, carList, answer);
            default -> System.out.println("Type in the right number");
        }
        //tools.customizedButton(80, 1, carInfo + " is deleted");


    }

    private String getSimpleName(ArrayList<Car> carList, String regNum) {
        String simpleName = null;
        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNum)) {
                System.out.println(carList.get(i));

                if (carList.get(i).getClass().getSimpleName().equalsIgnoreCase("luxury")) {
                    simpleName = "Luxury";
                    return simpleName;
                } else if (carList.get(i).getClass().getSimpleName().equalsIgnoreCase("Sport")) {
                    simpleName = "Sport";
                    return simpleName;
                } else if (carList.get(i).getClass().getSimpleName().equalsIgnoreCase("Family")) {
                    simpleName = "Family";
                    return simpleName;
                }
            }
        }
        return simpleName;
    }
 */