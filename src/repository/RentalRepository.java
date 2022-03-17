package repository;

import UI.UITools;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalRepository {
    UITools tools = new UITools();
    Scanner userInput = new Scanner(System.in);

    CarRepository carRepository;
    CustomerRepository customerRepository;


    public void viewRentals(ArrayList<Rental> rentalList) {
        tools.margeTop(120);
        for (int i = 0; i < rentalList.size(); i++) {

            if (rentalList.get(i).getCar().getClass().getSimpleName().equals("Luxury")) {
                System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                        "RegNumb", "Brand", "Model", "RegDate", "kmdriven", ">3000CCM", "Auto-gear", "CruiseContr.", "LeatherSeats");
                System.out.println(rentalList.get(i).getCar().toString());

            } else if (rentalList.get(i).getCar().getClass().getSimpleName().equals("Family")) {
                System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                        "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Air-Cond.", "CruiseContr.", ">7Seats");
                System.out.println(rentalList.get(i).getCustomer().toString());
                tools.margeTop(120);


            } else if (rentalList.get(i).getCar().getClass().getSimpleName().equals("Sport")) {
                System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s |\n",
                        "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Over200HP.");
                System.out.println(rentalList.get(i).getCustomer().toString());
                tools.margeTop(80);

            }
            System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                    "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity", "PhoneNumb", "MobileNumb",
                    "Email");
            System.out.println(rentalList.get(i).getCustomer().toString());
            tools.margeTop(120);

            System.out.printf("\n| %-25s %-25s %-25s %-25s   |", "rental_id",
                    "fromDateAndTime=", "toDateAndTime=", "maxKm");
            tools.margeTop(120);
            System.out.printf("\n| %-25s %-25s %-25s %-25s   |\n", rentalList.get(i).getRental_id(),
                    rentalList.get(i).getFromDateAndTime(), rentalList.get(i).getToDateAndTime(),
                    rentalList.get(i).getMaxKm());
        }
    }


    public void createRentalContract(ArrayList<Rental> rentalList, ArrayList<Car> carList, ArrayList<Customer> customerList) {
        String newCustomer = tools.returnStringInfo(50, 1, "Are you a returning customer");

        if (newCustomer.equalsIgnoreCase("yes")) {

            carRepository = new CarRepository();
            customerRepository = new CustomerRepository();

            int rental_id = tools.returnIntInfo(50, 1, "Enter rental id");
            //auto increment

            Car car = carRepository.getCar(carList, tools);

            Customer customer = customerRepository.getCustomer(customerList, tools);

            String fromDateAndTime = tools.returnStringInfo(50, 1, "Start date");

            String toDateAndTime = tools.returnStringInfo(50, 1, "End date");

            int maxKm = tools.returnIntInfo(50, 1, "Max KM");

            Rental rental = new Rental(car, customer, rental_id, fromDateAndTime,
                    toDateAndTime, maxKm);

            viewRental(rental);
            rentalList.add(rental);
        } else {
            System.out.println("Create new customer menu: not live yet!");
        }
    }

    public void populateRentalContractsToArrayList(Statement statement, ArrayList<Rental> rentalList) {
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

    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList, Scanner userInput) {

        for (int i = 0; i < rentalList.size(); i++) {
            System.out.println(rentalList.get(i));
        }

        System.out.println("Enter which rental_id needs to be updated");
        int answer = userInput.nextInt();

        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getRental_id() == answer)
                System.out.println(rentalList.get(i));
        }
        System.out.println("What do you want to update?\n" +
                "1 for RentFDate\n2 for RentTDate\n3 for RentMaxKm\n4 for lName\n5 for zipCode");

        int ans = userInput.nextInt();

        String newValue = null;
        String newVariable = null;

        switch (ans) {

            case 1:
                System.out.println("Enter which driver license number to be updated");
                newValue = userInput.next();
                newVariable = "customer_driver_license_number";
                break;
            case 2:
                System.out.println("Enter customer driver since number");
                newValue = userInput.next();
                newVariable = "customer_driver_since_number";
                break;
            case 3:
                System.out.println("Enter new first name");
                newValue = userInput.next();
                newVariable = "customer_first_name";
                break;
            case 4:
                System.out.println("Enter new last name");
                newValue = userInput.next();
                newVariable = "customer_last_name";
                break;
            case 5:
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
            System.out.println("Could not update rentals contract table");
        }
    }

    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList, Scanner userInput) throws SQLException {
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

    public void viewRentalContracts(Statement statement, ArrayList<Rental> rentalList, UITools tools) {
        System.out.println();
        tools.customizedButton(50, 1, "Rental Contracts");

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

    public void viewRental(Rental rental) {
        tools.margeTop(120);

        if (rental.getCar().getClass().getSimpleName().equals("Luxury")) {
            System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                    "RegNumb", "Brand", "Model", "RegDate", "kmdriven", ">3000CCM", "Auto-gear", "CruiseContr.", "LeatherSeats");
            System.out.println(rental.getCar().toString());

        } else if (rental.getCar().getClass().getSimpleName().equals("Family")) {
            System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                    "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Air-Cond.", "CruiseContr.", ">7Seats");
            System.out.println(rental.getCar().toString());
            tools.margeTop(120);


        } else if (rental.getCar().getClass().getSimpleName().equals("Sport")) {
            System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s |\n",
                    "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Over200HP.");
            System.out.println(rental.getCar().toString());
            tools.margeTop(80);

        }
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity", "PhoneNumb", "MobileNumb",
                "Email");
        System.out.println(rental.getCustomer().toString());
        tools.margeTop(120);

        System.out.printf("\n| %-25s %-25s %-25s %-25s   |", "rental_id",
                "fromDateAndTime=", "toDateAndTime=", "maxKm");
        tools.margeTop(120);
        System.out.printf("\n| %-25s %-25s %-25s %-25s   |", rental.getRental_id(),
                rental.getFromDateAndTime(), rental.getToDateAndTime(), rental.getMaxKm());

    }
}



