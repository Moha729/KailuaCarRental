package repository;

import UI.UITools;
import models.Car;
import models.Customer;
import models.Rental;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalRepository {
    UITools tools = new UITools();
    //Scanner userInput = new Scanner(System.in);

    CarRepository carRepository = new CarRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    DBRentalRepo rentalRepo = new DBRentalRepo();


    public void populateRentalContractsToArrayList(Statement statement, ArrayList<Rental> rentalList,
                                        ArrayList<Car> carList, ArrayList<Customer> customerList) {
        rentalRepo.populateRentals(rentalList, statement, carList, customerList);
    }

    public void createRentalContract(ArrayList<Rental> rentalList, ArrayList<Car> carList,
                                     ArrayList<Customer> customerList, Statement statement) {
        String newCustomer = tools.returnStringInfo(50, 1, "Are you a returning customer");

        if (newCustomer.equalsIgnoreCase("yes")) {

//            int rental_id = tools.returnIntInfo(50, 1, "Enter rental id");
            //auto increment

            Car car = carRepository.getCar(carList, tools);

            Customer customer = customerRepository.getCustomer(customerList, tools);

            String fromDateAndTime = tools.returnStringInfo(50, 1, "Start date");

            String toDateAndTime = tools.returnStringInfo(50, 1, "End date");

            int maxKm = tools.returnIntInfo(50, 1, "Max KM");

            Rental rental = new Rental(car, customer, 44, fromDateAndTime,
                    toDateAndTime, maxKm);

            viewRental(rental);
            rentalList.add(rental);

            rentalRepo.addRentalToDB(rental, statement);

        } else {
            System.out.println("Create new customer menu: not live yet!");
        }
    }

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
                    "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity",
                    "PhoneNumb", "MobileNumb", "Email");
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

    public Rental getRental(ArrayList<Rental> rentalList, UITools tools) {
        Rental rental = null;
        viewRentalsInMain(rentalList, tools);
        int rentalId = tools.returnIntInfo(50, 1, "Enter rental id number x");

        for (int i = 0; i < rentalList.size(); i++) {
            if (rentalList.get(i).getRental_id() == rentalId) {
                rental = rentalList.get(i);
                return rental;
            }
        }
        return rental;
    }


    public void updateRentalContracts(Statement statement, ArrayList<Rental> rentalList,
                                      Scanner userInput, ArrayList<Car> carList) {

        Rental rental = getRental(rentalList, tools);
        int answer = rental.getRental_id();

        System.out.println("What do you want to update?\n" +
                "1 to change car\n2 for new start date\n3 for new end date\n4 for new max KM");

        int ans = userInput.nextInt();

        String newValue = null;
        String newVariable = null;

        switch (ans) {

            case 1:
                System.out.println("Choose new car");
                Car changeCar = carRepository.getCar(carList, tools);
                //System.out.println(changeCar);
                newValue = changeCar.getRegistrationNumber();
                newVariable = "registration_number";
                rental.setCar(changeCar);
                break;
            case 2:
                System.out.println("Enter new from date");
                newValue = userInput.next();
                newVariable = "rental_from_date";
                rental.setFromDateAndTime(newValue);
                break;
            case 3:
                System.out.println("Enter new end date");
                newValue = userInput.next();
                newVariable = "rental_to_date";
                rental.setToDateAndTime(newValue);
                break;
            case 4:
                System.out.println("Enter new max KM");
                int newKm = userInput.nextInt();
                newValue = String.valueOf(newKm);
                newVariable = "rental_max_km";
                rental.setMaxKm(newKm);
                break;

        }
        rentalRepo.updateRental(statement, newVariable, newValue, answer);

    }

    public void deleteRentalContract(Statement statement, ArrayList<Rental> rentalList) {

        Rental rental = getRental(rentalList, tools);
        int answer = rental.getRental_id();

        rentalRepo.deleteRental(statement, answer);
                tools.customizedButton(30, 1, "Rental id: "+ answer + " is deleted");
                rentalList.remove(rental);


    }

    public void viewRentalContracts(ArrayList<Rental> rentalList, UITools tools) {
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
            System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %28s|\n",
                    "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Over200HP", " ");
            System.out.println(rental.getCar().toString());
            tools.margeTop(120);

        }
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "DriverNumb", "DriverSince", "Fname", "Lname", "ZipCode", "CustomCity", "PhoneNumb", "MobileNumb",
                "Email");
        System.out.println(rental.getCustomer().toString());
        tools.margeTop(120);

        System.out.printf("\n| %-28s %-28s %-28s %-28s    |\n", "rental_id",
                "fromDateAndTime=", "toDateAndTime=", "maxKm");
        //tools.margeTop(120);
        System.out.printf("| %-28s %-28s %-28s %-28s    |\n", rental.getRental_id(),
                rental.getFromDateAndTime(), rental.getToDateAndTime(), rental.getMaxKm());
        tools.margeTop(120);
        System.out.println();

    }

    public void viewRentalsInMain(ArrayList<Rental> rentalList, UITools tools) {
        tools.whiteSpace();
        tools.whiteSpace();
        tools.customizedButton(120, 1, "Active rentals");

        for (int i = 0; i < rentalList.size(); i++) {
            System.out.println();
            tools.customizedButton(30, 1, "Rental id: " + rentalList.get(i).getRental_id());
            tools.margeTop(50);
            System.out.printf("\n| %-15s %-20s %-20s %-20s |\n| %-15s %-20s %-20s %-20s |\n" +
                            "| %-15s %-20s %-20s %-20s |\n| %-15s %-20s %-20s %-20s |\n" +
                            "| %-15s %-20s %-20s %-20s |\n| %-15s %-20s %-20s %-20s |\n" +
                            "| %-15s %-20s %-20s %-20s |\n",
                    " ", "Car brand", "Car model", "Registration number",
                    "Car info: ", rentalList.get(i).getCar().getBrand(), rentalList.get(i).getCar().getModel(), rentalList.get(i).getCar().getRegistrationNumber(),
                    " ", "Customer name ", "Licence number", " ",
                    "Customer info: ", rentalList.get(i).getCustomer().getName(), rentalList.get(i).getCustomer().getDriverLicenseNumber(), " ",
                    "Date start: ", rentalList.get(i).getFromDateAndTime(), " ", " ",
                    "Date end: ", rentalList.get(i).getToDateAndTime(), " ", " ",
                    "Max km: ", rentalList.get(i).getMaxKm(), " ", " ");

            tools.margeTop(80);

        }
        System.out.println();
/*
        System.out.println();
        tools.customizedButton(50,1, "Luxury");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", ">3000CCM", "Auto-gear", "CruiseContr.", "LeatherSeats");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Luxury")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }*/
    }
}



