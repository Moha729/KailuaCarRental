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
    LuxuryService luxuryService = new LuxuryService();
    SportService sportService = new SportService();
    FamilyService familyService = new FamilyService();


    public void createCar(Statement statement, Scanner userInput, ArrayList<Car> carList) {

        try {

            int chooseType = tools.returnIntInfo(100,1, "Which type is it? - Enter 1 for Luxury - Enter 2 for Sport - Enter 3 for Family");

            String registrationNumber = tools.returnStringInfo(50,1, "Enter registration number");

            String brand = tools.returnStringInfo(50,1, "Enter brand");

            String model =  tools.returnStringInfo(50,1, "Enter model");

            String registrationDate  = tools.returnStringInfo(50,1, "Enter date");

            int kmDriven = tools.returnIntInfo(50,1, "Enter km driven");

            if(chooseType == 1){

                Luxury luxuryCar = luxuryService.createLuxury(statement, carList, userInput, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(luxuryCar);


            } else if(chooseType == 2){

                Sport sportsCar = sportService.createSportsCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven);
                carList.add(sportsCar);

            } else if(chooseType == 3){

                Family familyCar = familyService.createFamilyCar(statement, userInput, carList, registrationNumber, brand, model,
                        registrationDate, kmDriven, tools);

            } else { tools.customizedButton(60, 1, "Try again!");

            }

        } catch (SQLException e) {
            tools.customizedButton(60, 1, e.getMessage());
        }

    }

    public void updateCar(Statement statement, Scanner userInput, ArrayList<Car> carList){
        System.out.println("1 luxury 2 sport 3 family");
        switch (userInput.nextInt()){
            case 3 -> familyService.updateFamilyCar(statement, carList, userInput);
        }

    }
}
