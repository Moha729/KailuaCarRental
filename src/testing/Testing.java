package testing;

import models.Car;
import models.Luxury;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Testing {

        Scanner userInput = new Scanner(System.in);
        Luxury luxury = new Luxury();

    public void update(Statement statement, ArrayList<Car> carList) throws SQLException {

        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }

        System.out.println("Enter which registration number to be updated");
        String answer = userInput.next();

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer))
                carList.remove(i);
                System.out.println(carList.get(i));
        }
        System.out.println("Enter new registration number");
        String newNumber = userInput.next();
        System.out.println("Enter new brand");
        String newBrand =  userInput.next();

        System.out.println("Enter new model");
        String newModel = userInput.next();

        System.out.println("Enter new registration date");
        String regDate = userInput.next();

        System.out.println("Enter new km");
        int km = userInput.nextInt();

        System.out.println("Enter new ccm");
        String ccm = userInput.next();
        System.out.println("Enter if automatic gear");
        String automatic = userInput.next();
        System.out.println("Enter if cruise control ");
        String cruise = userInput.next();
        System.out.println("Enter if leather seats");
        String leather = userInput.next();

        statement.execute("UPDATE car_table SET " +
                "  registration_number='" + newNumber + "' , "
                + "brand='" + newBrand + "' , "
                + "model='" + newModel + "' , "
                + "registration_date='" + regDate + "' , "
                + "kmDriven ='" + km + "' "
                + "WHERE registration_number ='" +answer+"'");

        statement.execute("UPDATE luxury_cars SET "+
                "registration_number='" + newNumber + "' , "
                + "ccm='" + ccm + "' , "
                + "automatic_gear='" + automatic + "' , "
                + "cruise_control='" + cruise + "' , "
                + "leather_seats='" + leather + "'"
                + "WHERE registration_number ='" + answer + "'");
        statement.close();

}

    public void populateArrayList(Statement statement,ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table LEFT JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                            luxury = new Luxury(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("kmDriven"),
                            resultSet.getBoolean("ccm"),
                            resultSet.getBoolean("automatic_gear"),
                            resultSet.getBoolean("cruise_control"),
                            resultSet.getBoolean("leather_seats"));

                      carList.add(luxury);
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

    public void deleteLuxury(Statement statement,ArrayList<Car> carList) throws SQLException {
        System.out.println("Enter a registration number to delete its car information");
        String answer = userInput.next();
        statement.execute("DELETE FROM car_table WHERE registration_number = '"+answer+"'");
        statement.execute("DELETE FROM luxury_cars WHERE registration_number = '"+answer+"'");
        for (int i = 0; i < carList.size()-1; i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer));
                carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
        }


    }
}
