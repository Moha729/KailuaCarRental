package repository;


import java.sql.SQLException;
import java.sql.Statement;

public class DBRepo {


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

    public void updateFamily(Statement statement, String newVariable, String newValue, String answer) {

        try {
            statement.execute("UPDATE family_table SET " +
                    newVariable + " = '" + newValue + "' " +
                    "WHERE registration_number ='" + answer + "'");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Could not update family car");
        }
    }

}
