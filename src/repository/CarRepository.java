package repository;

import UI.MoTools;
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

    LuxuryService luxuryService = new LuxuryService();
    SportService sportService = new SportService();
    FamilyService familyService = new FamilyService();

    public void populateCars(Statement statement, ArrayList<Car> carList) {
        luxuryService.populateLuxuryToArrayList(statement, carList);
        familyService.populateFamilyToArrayList(statement, carList);
        sportService.populateSportToArrayList(statement, carList);
    }

    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList, MoTools tools) throws SQLException {

        //System.out.println("");
        System.out.println();
        tools.customizedButton(120, 1, "What is the type of the new car?");

        System.out.print(tools.dobbleButton(">1< Luxury", ">2< Family"));
        System.out.print(tools.dobbleButton(">3< Sport", ">4< \"Back\""));
        //int chooseType = tools.returnIntInfo(120, 3, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");
        int chooseType = userInput.nextInt();

        //if (chooseType != 1 || chooseType != 2 || chooseType != 3) {return;}

        String registrationNumber = tools.returnStringInfo(60, 1, "Enter registration number");

        String brand = tools.returnStringInfo(60, 1, "Enter brand");

        String model = tools.returnStringInfo(60, 1, "Enter model");

        String registrationDate = tools.returnStringInfo(60, 1, "Enter date");

        int kmDriven = tools.returnIntInfo(60, 1, "Enter km driven");
        //try {
        if (chooseType == 1) {

            Luxury luxuryCar = luxuryService.createLuxury(statement, carList, userInput, registrationNumber, brand, model,
                    registrationDate, kmDriven);
            carList.add(luxuryCar);


        } else if (chooseType == 3) {

            Sport sportsCar = sportService.createSportsCar(statement, userInput, carList, registrationNumber, brand, model,
                    registrationDate, kmDriven);
            carList.add(sportsCar);

        } else if (chooseType == 2) {

            Family familyCar = familyService.createFamilyCar(statement, userInput, carList, registrationNumber, brand, model,
                    registrationDate, kmDriven, tools);
            carList.add(familyCar);

        } else {
            tools.customizedButton(60, 1, "Try again!");

        }
        //} catch (SQLException e) {tools.customizedButton(60, 1, e.getMessage());}

    }

    public void viewCars(ArrayList<Car> carList, MoTools tools) {
        luxuryService.viewLuxuryCars(carList, tools);
        familyService.viewFamilyCars(carList, tools);
        sportService.viewSportCars(carList, tools);
    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {
        System.out.println("1 luxury 2 sport 3 family");
        switch (userInput.nextInt()) {
            case 1 -> luxuryService.updateLuxuryCar(statement, carList, userInput);
            case 2 -> sportService.updateSportCar(statement, carList, userInput);
            case 3 -> familyService.updateFamilyCar(statement, carList, userInput);
        }

    }

    public void delete(Statement statement, ArrayList<Car> carList, Scanner userInput) throws SQLException {
        String answer;
        System.out.println(carList);
        System.out.println("Enter 1 - to delete a luxury car\nEnter 2 - to delete a sport car\n Enter 3 - to delete a family car");

        switch (userInput.nextInt()) {
            case 1 -> {
                System.out.println("Enter a registration number to delete its car information");
                answer = userInput.next();
                statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
                statement.execute("DELETE FROM luxury_cars WHERE registration_number = '" + answer + "'");
                for (int i = 0; i < carList.size() - 1; i++) {
                    if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) ;
                    carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
                }
            }
            case 2 -> {
                System.out.println("Enter a registration number to delete its car information");
                answer = userInput.next();
                statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
                statement.execute("DELETE FROM sport_cars WHERE registration_number = '" + answer + "'");
                for (int i = 0; i < carList.size() - 1; i++) {
                    if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) ;
                    carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
                }
            }
            case 3 -> {
                System.out.println("Enter a registration number to delete its car information");
                answer = userInput.next();
                statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
                statement.execute("DELETE FROM family_cars WHERE registration_number = '" + answer + "'");
                for (int i = 0; i < carList.size() - 1; i++) {
                    if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) ;
                    carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
                }
            }
            default -> System.out.println("Type in the right number");
        }

    }

}
