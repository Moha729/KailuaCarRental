package testing;

import models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Testing {

        Scanner userInput = new Scanner(System.in);

    public void update(Statement statement, ArrayList<Car> carList) throws SQLException {


        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }

        System.out.println("Enter which registration number to be updated");
        String answer = userInput.next();

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer))
                System.out.println(carList.get(i));
        }
        System.out.println("Enter new registration number");
        String newNumber = userInput.next();

        System.out.println("Enter new brand");
        String newBrand = userInput.next();

        System.out.println("Enter new model");
        String newModel = userInput.next();

        System.out.println("Enter new registration date");
        String regDate = userInput.next();

        System.out.println("Enter new km");
        int km = userInput.nextInt();


        statement.execute("UPDATE car_table SET " +
                "  registration_number='" + newNumber + "' , "
                + "brand='" + newBrand + "' , "
                + "model='" + newModel + "' , "
                + "registration_date='" + regDate + "' , "
                + "kmDriven ='" + km + "'"
                + "WHERE registration_number ='" + answer + "'");

    }

    public void populateArrayList(Statement statement,ArrayList<Car> carList, Car readCars) { // table content
        try {
            statement.execute("SELECT * FROM car_table");
            ResultSet resultSet = statement.getResultSet();
            if (resultSet != null)
                while (resultSet.next()) {
                    readCars = new Car(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("kmDriven"));



                    carList.add(readCars);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }


    public void join(Statement statement){
        try {
            String sql = "SELECT * FROM car_table  LEFT JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("reg  brand   model    regdate    kmdriven    ccm   gear   cruise    leather");
            if (resultSet != null)
                while (resultSet.next()) {

                    String registration_number =   resultSet.getString("registration_number");
                    String brand =     resultSet.getString("brand");
                    String model =     resultSet.getString("model");
                    String registration_date =  resultSet.getString("registration_date");
                    int kmDriven =     resultSet.getInt("kmDriven");
                    String ccm =       resultSet.getString("ccm");
                    String gear =      resultSet.getString("automatic_gear");
                    String cruise_control =  resultSet.getString("cruise_control");
                    String leather_seats =  resultSet.getString("leather_seats");

                    System.out.println(registration_number +" / " +brand + " / "+ model + " / " + registration_date+" / "
                            +kmDriven+" / "+ ccm +" / " +gear+" / " + cruise_control+ " / " + leather_seats+" / ");

                }


            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}
