package service;

import UI.MoTools;
import models.Car;
import models.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SportService {
    MoTools tools = new MoTools();
    Scanner userInput = new Scanner(System.in);

    public Sport createSportsCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String reg, String br, String mo,
                         String regDate, int kmDr) throws SQLException{

        String gear = tools.returnStringInfo(50, 1, "does it have a manual gear?");
        boolean gearGear;
        if (gear.equalsIgnoreCase("yes")){
            gearGear = true;
        }else {
            gearGear = false;
        }

        String horsePower = tools.returnStringInfo(50, 1, "does it have over 200 hP?");
        boolean hpHp;
        if (horsePower.equalsIgnoreCase("Yes")){
            hpHp = true;
        } else {
            hpHp = false;
        }

        Sport sportsCar = new Sport(reg, br , mo, regDate, kmDr, gearGear, hpHp);

        addSportsCarToDB(sportsCar, statement);

        return sportsCar;
    }
    private void addSportsCarToDB(Sport sportsCar, Statement statement) throws SQLException {
        statement.execute("INSERT INTO car_table " + "(registration_number, brand, model, registration_date, kmDriven)" + "" +
                "VALUES('"
                + sportsCar.getRegistrationNumber()   + "','"
                + sportsCar.getBrand()                +  "','"
                + sportsCar.getModel()                + "','"
                + sportsCar.getRegistrationDate()     + "','"
                + sportsCar.getKmDriven()             + "')");

        statement.execute("INSERT INTO  sport_cars " + "(registration_number, ManualGear, Over200HP)" + "" +
                "VALUES('"
                + sportsCar.getRegistrationNumber() + "','"
                + sportsCar.isManualGear()       + "','"
                + sportsCar.isOver200HP()        + "')");
    }

    /*public void showSportCars(Statement statement, ArrayList<Car> carList)throws SQLException{
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        String name = userInput.next();

        statement.execute("SELECT * FROM cars WHERE registration_number = '"+ name+"'");
    }*/
    
    public void updateSportCars(Statement statement, ArrayList<Car> carList) throws SQLException{

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
        String newBrand =  userInput.next();

        System.out.println("Enter new model");
        String newModel = userInput.next();

        System.out.println("Enter new registration date");
        String regDate = userInput.next();

        System.out.println("Enter new km");
        int km = userInput.nextInt();

        System.out.println("Enter if it has Manual gear");
        String ManualGear = userInput.next();
        System.out.println("Enter if it has over 200 Horsepower");
        String Over200HP = userInput.next();


        statement.execute("UPDATE car_table SET " +
                "  registration_number='" + newNumber + "' , "
                + "brand='" + newBrand + "' , "
                + "model='" + newModel + "' , "
                + "registration_date='" + regDate + "' , "
                + "kmDriven ='" + km + "' "
                + "WHERE registration_number ='" +answer+"'");

        statement.execute("UPDATE luxury_cars SET "+
                "registration_number='" + newNumber + "' , "
                + "ManualGear='" + ManualGear + "' , "
                + "Over200Hp='" + Over200HP + "' , "
                + "WHERE registration_number ='" + answer + "'");
        statement.close();
    }
    public void populateSportToArrayList(Statement statement, ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table LEFT JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    Sport sport = new Sport(
                            resultSet.getString("registration_number"),
                            resultSet.getString("brand"),
                            resultSet.getString("model"),
                            resultSet.getString("registration_date"),
                            resultSet.getInt("kmDriven"),
                            resultSet.getBoolean("manualGear"),
                            resultSet.getBoolean("over200HP"));

                    carList.add(sport);
                }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}
