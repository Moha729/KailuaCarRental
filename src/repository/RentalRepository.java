package repository;

import UI.MoTools;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalRepository {
    MoTools tools = new MoTools();
    Scanner userInput = new Scanner(System.in);

    public void createRentalContract(Car car, Customer customer, ArrayList<Rental> rentalList){

        int rental_id = tools.returnIntInfo(50, 1, "Enter rental id");

        String fromDateAndTime = tools.returnStringInfo(50, 1, "Enter first name");

        String toDateAndTime = tools.returnStringInfo(50, 1, "Enter last name");

        int maxKm = tools.returnIntInfo(50, 1, "Enter zip code ");

        Rental rental = new Rental(car, customer, rental_id, fromDateAndTime, toDateAndTime, maxKm);
        rentalList.add(rental);


    }
    public void populateRentalContractsToArrayList(Statement statement, ArrayList<Rental> rentalList){
        try {

            String sql = ("SELECT * FROM rental_table " +
                    "INNER JOIN customer_table " +
                    "ON customer_table.customer_driver_license_number = rental_table.rental_id");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Rental rental = new Rental(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getString("customer_driver_license_number"),
                            resultSet.getString("customer_driver_since_number"),
                            resultSet.getString("customer_first_name "),
                            resultSet.getString("customer_last_name"),
                            resultSet.getInt("customer_zip_code"),
                            resultSet.getString("customer_city"),
                            resultSet.getInt("customer_phone_number"),
                            resultSet.getInt("customer_mobile_number"),
                            resultSet.getString("customer_email"),
                            resultSet.getInt("rental_id"),
                            resultSet.getString("rental_from_date"),
                            resultSet.getString("rental_to_date"),
                            resultSet.getInt("rental_max_km"));

                    rentalList.add(rental);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
    public void viewRentalContracts(Statement statement, ArrayList<Rental> rentalList, MoTools tools){
        System.out.println();
        tools.customizedButton(50,1, "Rental Contracts");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s |\n",
                "RentFDate", "RentTDate", "RentMaxKm");
        tools.margeTop(120);

        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getClass().getSimpleName().equals("Rental")) {
                System.out.println("\n" + rentalList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }
    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput){

        for (int i = 0; i < rentalList.size(); i++) {
            System.out.println(rentalList.get(i));
        }

        System.out.println("Enter which rental_id needs to be updated");
        int answer = userInput.nextInt();

        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getRental_id()== answer)
                System.out.println(rentalList.get(i));
        }
        System.out.println("What do you want to update?\n" +
                "1 for RentFDate\n2 for RentTDate\n3 for RentMaxKm\n4 for lName\n5 for zipCode");

        int ans = userInput.nextInt();

        String newValue = null;
        String newVariable = null;

        switch (ans){

            case 1 :
                System.out.println("Enter which driver license number to be updated");
                newValue = userInput.next();
                newVariable = "customer_driver_license_number";
                break;
            case 2 :
                System.out.println("Enter customer driver since number");
                newValue =  userInput.next();
                newVariable = "customer_driver_since_number";
                break;
            case 3 :
                System.out.println("Enter new first name");
                newValue = userInput.next();
                newVariable = "customer_first_name";
                break;
            case 4 :
                System.out.println("Enter new last name");
                newValue = userInput.next();
                newVariable = "customer_last_name";
                break;
            case 5 :
                System.out.println("Enter new customer zip code");
                newValue = userInput.next();
                newVariable = "customer_zip_code";
                break;
        }

        try {
            statement.execute("UPDATE rental_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE rental_id ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update rental contract table");
        }
        //statement.close();
    }
    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList, Scanner userInput) throws SQLException{
        int answer = userInput.nextInt();
        System.out.println(rentalList);
        System.out.println("Enter the rental id for the contract you want to delete");

        answer = userInput.nextInt();
        statement.execute("DELETE FROM rental_table WHERE rental_id = '" + answer + "'");
        for (int i = 0; i < rentalList.size() - 1; i++) {
            if (rentalList.get(i).getRental_id() == answer) {
                rentalList.remove(i);
            }
        }
    }
}
