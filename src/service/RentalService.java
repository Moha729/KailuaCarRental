package service;

import UI.UITools;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {
    UITools tools = new UITools();
    RentalService rentalService;

    public void RentalService(){
        rentalService = new RentalService();
    }
    public void createRentalContract(Car car, Customer customer, ArrayList<Rental> rentalList){
        rentalService.createRentalContract(car, customer, rentalList);
    }
    public void populateRentalContractsToArrayList(Statement statement, ArrayList<Rental> rentalList){
        rentalService.populateRentalContractsToArrayList(statement, rentalList);
    }
    public void viewRentalContracts(Statement statement, ArrayList<Rental> rentalList, UITools tools){
        rentalService.viewRentalContracts(statement, rentalList, tools);
    }
    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){
        rentalService.updateRentalContracts(statement, rentalList, userInput);
    }
    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){
        rentalService.deleteRentalContract(statement, rentalList, userInput);
    }
}
