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

    public void createRentalContract(ArrayList<Rental> rentalList,ArrayList<Car> carList,ArrayList<Customer> customerList){
        rentalRepository.createRentalContract(rentalList,carList,customerList);
    }
    public void populateRentalContractsToArrayList(ArrayList<Rental> rentalList, ArrayList<Car> carList, ArrayList<Customer> customerList){
        rentalRepository.populateRentalContractsToArrayList(rentalList, carList, customerList);
    }
    public void viewRentals(ArrayList<Rental> rentalList, UITools tools){
        rentalRepository.viewRentalsInMain(rentalList, tools);
        rentalRepository.chooseRental(rentalList);
    }
    public void updateRentalContracts(ArrayList<Rental> rentalList, Scanner userInput, ArrayList<Car> carList){
        rentalRepository.updateRentalContracts(rentalList, userInput, carList);
    }
    public void deleteRentalContract(ArrayList<Rental> rentalList) throws SQLException {
        rentalRepository.deleteRentalContract(rentalList);
    }
}
