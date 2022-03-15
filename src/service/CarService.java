package service;

import UI.MoTools;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.SQLException;
import java.sql.Statement;

public class CarService {

    MoTools tools = new MoTools();

    LuxuryService luxuryService = new LuxuryService(); // Luxury cars Service class
    SportService sportService = new SportService(); // Sport cars service class
    FamilyService familyService = new FamilyService();// family cars service class

    public void populateCars(Statement statement, ArrayList<Car> carList){
        luxuryService.populateLuxuryToArrayList(statement,carList);
        familyService.populateFamilyToArrayList(statement, carList);
        sportService.populateSportToArrayList(statement,carList);
    }



    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {

        try {

            int chooseType = tools.returnIntInfo(100, 1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");

            String registrationNumber = tools.returnStringInfo(50, 1, "Enter registration number");

            String brand = tools.returnStringInfo(50, 1, "Enter brand");

            String model = tools.returnStringInfo(50, 1, "Enter model");

            String registrationDate = tools.returnStringInfo(50, 1, "Enter date");

            int kmDriven = tools.returnIntInfo(50, 1, "Enter km driven");

            if (chooseType == 1) {

                Luxury luxuryCar = luxuryService.createLuxury(statement, carList, userInput, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(luxuryCar);


            } else if (chooseType == 2) {

                Sport sportsCar = sportService.createSportsCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(sportsCar);

            } else if (chooseType == 3) {

                Family familyCar = familyService.createFamilyCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven, tools);

            } else {
                tools.customizedButton(60, 1, "Try again!");

            }

        } catch (SQLException e) {
            tools.customizedButton(60, 1, e.getMessage());
        }

    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {
        System.out.println("1 luxury 2 sport 3 family");
        switch (userInput.nextInt()) {
            case 1 -> luxuryService.updateLuxuryCar(statement,carList,userInput);
            case 2 -> sportService.updateSportCar(statement,carList,userInput);
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