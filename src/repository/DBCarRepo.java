package repository;


import db.DBManager;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DBCarRepo {

    public void populateFamilyCarFromArraylist(Statement statement, ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN family_cars ON car_table.registration_number = family_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Family family = new Family(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("manual_gear"),
                            resultSet.getBoolean("air_condition"),
                            resultSet.getBoolean("cruise_control"),
                            resultSet.getBoolean("seven_seats_or_more"));
                    carList.add(family);
                }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void populateLuxuryCarFromArraylist(Statement statement, ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Luxury luxury = new Luxury(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
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

    public void populateSportFromArrayList(Statement statement, ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Sport sport = new Sport(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("km_driven"),
                            resultSet.getBoolean("manual_gear"),
                            resultSet.getBoolean("over200HP"));
                    carList.add(sport);
                }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }

    public void addFamilyCarToDB(Family familyCar, Statement statement) {
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.getBrand() + "','"
                    + familyCar.getModel() + "','"
                    + familyCar.getRegistrationDate() + "','"
                    + familyCar.getKmDriven() + "')");

            statement.execute("INSERT INTO  family_cars " + "(registration_number, manual_gear ,air_condition , cruise_control, seven_seats_or_more)" + "" +
                    "VALUES('"
                    + familyCar.getRegistrationNumber() + "','"
                    + familyCar.isManualGear() + "','"
                    + familyCar.isAirCondition() + "','"
                    + familyCar.isCruiseControl() + "','"
                    + familyCar.isSevenSeatsOrMore() + "')");

        } catch (SQLException sqlException) {
            System.out.println("Error adding family to database: " + sqlException);
            System.exit(1);
        }
    }

    public void addLuxuryCarToDB(Luxury luxuryCar, Statement statement) {
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number,brand,model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.getBrand() + "','"
                    + luxuryCar.getModel() + "','"
                    + luxuryCar.getRegistrationDate() + "','"
                    + luxuryCar.getKmDriven() + "')");
            /*System.out.println("Stop here");
            Scanner scanner = new Scanner(System.in);
            String go = scanner.next();*/
            statement.execute("INSERT INTO  luxury_cars " + "(registration_number, ccm, automatic_gear, cruise_control, leather_seats)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.isOver3000CCM() + "','"
                    + luxuryCar.isAutomaticGear() + "','"
                    + luxuryCar.isCruiseControl() + "','"
                    + luxuryCar.isLeatherSeats() + "')");
        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void addSportCarToDB(Sport sportsCar, Statement statement) {
        try {
            statement.execute("INSERT INTO car_table " + "(registration_number, brand, model, registration_date, km_driven)" + "" +
                    "VALUES('"
                    + sportsCar.getRegistrationNumber() + "','"
                    + sportsCar.getBrand() + "','"
                    + sportsCar.getModel() + "','"
                    + sportsCar.getRegistrationDate() + "','"
                    + sportsCar.getKmDriven() + "')");

            statement.execute("INSERT INTO  sport_cars " + "(registration_number, manual_gear, Over200HP)" + "" +
                    "VALUES('"
                    + sportsCar.getRegistrationNumber() + "','"
                    + sportsCar.isManualGear() + "','"
                    + sportsCar.isOver200HP() + "')");
        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void deleteFamilyCar(Statement statement, ArrayList<Car> carList, String answer) {

        try {
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM family_cars WHERE registration_number = '" + answer + "'");
        } catch (SQLException e) {
            System.out.println("No cars deleted");
        }
    }

    public void deleteLuxuryCar(Statement statement, ArrayList<Car> carList, String answer) {
        try {
            //Mardin Lav en klasse der laver præcis det samme som den her
            //og se om PreparedStatement virker bedre end det vi har her
//            PreparedStatement preparedStatement;
//            preparedStatement = DBManager.getConnection().prepareStatement("DELETE FROM car_table,sport_cars WHERE registration_number = ?");
//            preparedStatement.setString(1,answer);
//            preparedStatement.executeUpdate();
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM luxury_cars WHERE registration_number = '" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not delete the car");
        }
    }

    public void deleteSportCar(Statement statement, ArrayList<Car> carList, String answer) {
        try {
            statement.execute("DELETE FROM car_table WHERE registration_number = '" + answer + "'");
            statement.execute("DELETE FROM sport_cars WHERE registration_number = '" + answer + "'");
        } catch (SQLException e) {
            System.out.println("no cars deleted: " + e.getMessage());
        }
    }

    public void updateCar(Statement statement, String newVariable, String newValue, String answer) {
        try {
            statement.execute("UPDATE car_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car table");
        }
    }

    public void updateFamily(Statement statement, String dbColumn, String newValue, String answer) {
        try {
            statement.execute("UPDATE family_cars SET " +
                    dbColumn + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update family car");
        }
    }

    public void updateLuxuryCar(Statement statement, String newVariable, String newValue, String answer) {
        try {
            statement.execute("UPDATE luxury_cars SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update luxury table");
        }
    }

    public void updateSportCar(Statement statement, String newVariable, String newValue, String answer) {
        try {
            statement.execute("UPDATE sport_cars SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update sport car");
        }
    }

}
