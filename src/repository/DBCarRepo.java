package repository;


import models.Car;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBCarRepo {


    public void updateCar(Statement statement, String newVariable, String newValue, String answer) {

        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car table");
        }
    }


    public void deleteLuxuryCar(Statement statement, ArrayList<Car> carList, String answer){

        try {
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM luxury_cars WHERE registration_number = '" + answer + "'");
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) {
                    carList.remove(carList.get(i));
                    System.out.println("Car " + answer + " is deleted");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Could not delete the car");
        }
    }

    public void deleteSportCar(Statement statement, ArrayList<Car> carList, String answer){

        try {
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM sport_cars WHERE registration_number = '" + answer + "'");
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) {
                    carList.remove(carList.get(i));
                    System.out.println("Car " + answer + " is deleted");
                }
            }
        }catch (SQLException e){
            System.out.println("no cars deleted: "+e.getMessage());
        }
    }

    public void deleteFamilyCar(Statement statement, ArrayList<Car> carList, String answer){

        try{

        statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
                statement.execute("DELETE FROM family_cars WHERE registration_number = '" + answer + "'");
                for (int i = 0; i < carList.size(); i++) {
                    if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer)) {
                        carList.remove(carList.get(i));
                        System.out.println("Car " + answer + "is deleted");

                    }
                    }
    }catch(SQLException e){
            System.out.println("No cars deleted");
        }


    public void updateLuxuryCar(Statement statement, String newVariable, String newValue, String answer) {
        try {
            statement.execute("UPDATE luxury_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update luxury table");
        }


    }


    public void updateSportCar(Statement statement, String newVariable, String newValue, String answer) {

        try {
            statement.execute("UPDATE sport_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update sport car");
        }
    }

    public void updateFamily(Statement statement, String dbColumn, String newValue, String answer) {

        try {
            statement.execute("UPDATE family_table SET " +
                    dbColumn + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update family car");
        }
    }

}
