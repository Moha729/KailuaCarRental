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
        rentalRepository.createRentalContract(rentalList,carList,customerList);//new rental
    }
    public void populateRentalContractsToArrayList(Statement statement, ArrayList<Rental> rentalList){
        rentalRepository.populateRentalContractsToArrayList(statement, rentalList);//read from db
    }
    public void viewRentals(Statement statement, ArrayList<Rental> rentalList, UITools tools){
        rentalRepository.viewRentals(rentalList);//view
    }
    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){
        rentalRepository.updateRentalContracts(statement, rentalList, userInput);//update
    }
    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList, Scanner userInput) throws SQLException {
        rentalRepository.deleteRentalContract(statement, rentalList, userInput);//end
    }
}
