package service;

import UI.MoTools;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {
    MoTools tools = new MoTools();
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
    public void viewRentalContracts(Statement statement, ArrayList<Rental> rentalList, MoTools tools){
        rentalService.viewRentalContracts(statement, rentalList, tools);
    }
    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){
        rentalService.updateRentalContracts(statement, rentalList, userInput);
    }
    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){
        rentalService.deleteRentalContract(statement, rentalList, userInput);
    }
}
