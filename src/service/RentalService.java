package service;

import UI.UITools;
import models.Car;
import models.Customer;
import models.Rental;
import repository.RentalRepository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalService {
    RentalRepository rentalRepository;

    public RentalService(){
        rentalRepository = new RentalRepository();
    }

    public void createRentalContract(ArrayList<Rental> rentalList,ArrayList<Car> carList,ArrayList<Customer> customerList, Statement statement){
        rentalRepository.createRentalContract(rentalList,carList,customerList, statement);
    }
    public void populateRentalContractsToArrayList(ArrayList<Rental> rentalList, ArrayList<Car> carList, ArrayList<Customer> customerList){
        rentalRepository.populateRentalContractsToArrayList(rentalList, carList, customerList);
    }
    public void viewRentals(ArrayList<Rental> rentalList, UITools tools){
        rentalRepository.viewRentalsInMain(rentalList, tools);
    }
    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput, ArrayList<Car> carList){
        rentalRepository.updateRentalContracts(statement, rentalList, userInput, carList);
    }
    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList) throws SQLException {
        rentalRepository.deleteRentalContract(statement, rentalList);
    }
}
