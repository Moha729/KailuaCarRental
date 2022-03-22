package repository;


import db.DBManager;
import models.Car;
import models.Family;
import models.Luxury;
import models.Sport;

import java.sql.*;
import java.util.ArrayList;

public class DBCarRepo {
    Connection connection = DBManager.getConnection();


    public void populateFamilyCarToArraylist(ArrayList<Car> carList) {
        if (connection != null) {
        try {



                String sql = ("SELECT * FROM car_table INNER JOIN family_cars ON car_table.registration_number = family_cars.registration_number");
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(sql);
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
            } catch(SQLException e){
                System.out.println(e.getMessage() + "\n");
            }
        }
    }

    public void populateLuxuryCarToArraylist(ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN luxury_cars ON car_table.registration_number = luxury_cars.registration_number");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
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

    public void populateSportToArrayList(ArrayList<Car> carList) {
        try {
            String sql = ("SELECT * FROM car_table INNER JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
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

    public void addFamilyCarToDB(Family familyCar) {
        try {
            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO car_table " + "(registration_number, brand, model, registration_date, km_driven)" + "" +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,familyCar.getRegistrationNumber());
            preparedStatement.setString(2,familyCar.getBrand());
            preparedStatement.setString(3,familyCar.getModel());
            preparedStatement.setString(4,familyCar.getRegistrationDate());
            preparedStatement.setInt(5,familyCar.getKmDriven());
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("INSERT INTO family_cars " + "(registration_number, air_condition, cruise_control, seven_seats_or_more)" + "" +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,familyCar.getRegistrationNumber());
            preparedStatement.setBoolean(2,familyCar.isManualGear());
            preparedStatement.setBoolean(3,familyCar.isAirCondition());
            preparedStatement.setBoolean(4,familyCar.isCruiseControl());
            preparedStatement.setBoolean(5,familyCar.isSevenSeatsOrMore());
            preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println("Error adding family to database: " + sqlException);
            System.exit(1);
        }
    }

    public void addLuxuryCarToDB(Luxury luxuryCar) {
        try {

            PreparedStatement preparedStatement;
            preparedStatement = connection.prepareStatement("INSERT INTO car_table " + "(registration_number, brand, model, registration_date, km_driven)" + "" +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,luxuryCar.getRegistrationNumber());
            preparedStatement.setString(2,luxuryCar.getBrand());
            preparedStatement.setString(3,luxuryCar.getModel());
            preparedStatement.setString(4,luxuryCar.getRegistrationDate());
            preparedStatement.setInt(5,luxuryCar.getKmDriven());
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("INSERT INTO luxury_cars " + "(registration_number, ccm, automatic_gear,cruise_control,leather_seats)" + "" +
                    "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1, luxuryCar.getRegistrationNumber());
            preparedStatement.setBoolean(2,luxuryCar.isOver3000CCM());
            preparedStatement.setBoolean(3,luxuryCar.isAutomaticGear());
            preparedStatement.setBoolean(4,luxuryCar.isCruiseControl());
            preparedStatement.setBoolean(5,luxuryCar.isLeatherSeats());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void addSportCarToDB(Sport sportsCar) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO car_table " +
                    "(registration_number, brand, model, registration_date, km_driven)" + "" + "VALUES(?,?,?,?,?)");

            preparedStatement.setString(1,sportsCar.getRegistrationNumber());
            preparedStatement.setString(2,sportsCar.getBrand());
            preparedStatement.setString(3,sportsCar.getModel());
            preparedStatement.setString(4,sportsCar.getRegistrationDate());
            preparedStatement.setInt(5,sportsCar.getKmDriven());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("INSERT INTO sport_cars (registration_number, manual_gear, over200HP)" +
                    "VALUES(?,?,?)");

            preparedStatement.setString(1,sportsCar.getRegistrationNumber());
            preparedStatement.setBoolean(2,sportsCar.isManualGear());
            preparedStatement.setBoolean(3,sportsCar.isOver200HP());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("No cars added");
        }
    }

    public void deleteAllCar(String answer, String sqlTable_name){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM car_table WHERE registration_number = ?");
            preparedStatement.setString(1,answer);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM "+sqlTable_name+" WHERE registration_number = ?");
            preparedStatement.setString(1,answer);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("No cars deleted in Database");
        }
    }

    public void updateCar(String dbColumn, String newValue, String answer) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE car_table SET "+dbColumn+" =? "+ " WHERE registration_number =?");
            preparedStatement.setString(1,newValue);
            preparedStatement.setString(2,answer);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car");
        }
    }

    public void updateAllCar(String dbColumn, String newValue, String answer,
                             String sqlTable_name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE "+sqlTable_name+" SET "+dbColumn+" =? "+ " WHERE registration_number =?");
            preparedStatement.setString(1,newValue);
            preparedStatement.setString(2,answer);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update car");
        }
    }
}
