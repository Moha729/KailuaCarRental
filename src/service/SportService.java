package service;

import UI.MoTools;
import models.Car;
import models.Luxury;
import models.Sport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class SportService {
    MoTools tools = new MoTools();
    Scanner userInput = new Scanner(System.in);
    Sport sport;

    public Sport createSportsCar(Statement statement, Scanner userInput, ArrayList<Car> carList, String reg, String br, String mo,
                                 String regDate, int kmDr) throws SQLException {

        String gear = tools.returnStringInfo(50, 1, "does it have a manual gear?");
        boolean gearGear;
        if (gear.equalsIgnoreCase("yes")) {
            gearGear = true;
        } else {
            gearGear = false;
        }

        String horsePower = tools.returnStringInfo(50, 1, "does it have over 200 hP?");
        boolean hpHp;
        if (horsePower.equalsIgnoreCase("Yes")) {
            hpHp = true;
        } else {
            hpHp = false;
        }

        Sport sportsCar = new Sport(reg, br, mo, regDate, kmDr, gearGear, hpHp);

        addSportsCarToDB(sportsCar, statement);

        return sportsCar;
    }


    public void populateSportToArrayList(Statement statement,ArrayList<Car> carList) { // table content
        try {

            String sql = ("SELECT * FROM car_table INNER JOIN sport_cars ON car_table.registration_number = sport_cars.registration_number");
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet != null)
                while (resultSet.next()) {
                    sport = new Sport(
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

    private void addSportsCarToDB(Sport sportsCar, Statement statement) throws SQLException {
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
    }

    public void updateSportCars(Statement statement, ArrayList<Car> carList) throws SQLException {

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

        System.out.println("Enter if it has Manual gear");
        String ManualGear = userInput.next();

        System.out.println("Enter if it has over 200 Horsepower");
        String Over200HP = userInput.next();


        statement.execute("UPDATE car_table SET " +
                "  registration_number='" + newNumber + "' , "
                + "brand='" + newBrand + "' , "
                + "model='" + newModel + "' , "
                + "registration_date='" + regDate + "' , "
                + "km_driven ='" + km + "' "
                + "WHERE registration_number ='" + answer + "'");

        statement.execute("UPDATE sport_cars SET " +
                "registration_number='" + newNumber + "' , "
                + "manual_gear='" + ManualGear + "' , "
                + "Over200Hp='" + Over200HP + "' , "
                + "WHERE registration_number ='" + answer + "'");
        statement.close();
    }

    public void viewSportCars(ArrayList<Car> carList, MoTools tools) {
        System.out.println();
        tools.customizedButton(50, 1, "Sports/n");

        tools.margeTop(70);
        System.out.printf("\n| %-14s %-14s %-12s %-12s %-12s %-10s %-10s %-13s %-13s |\n",
                "RegNumb", "Brand", "Model", "RegDate", "kmdriven", "manu-gear", "Over200HP.");
        tools.margeTop(120);

        for (int i = 0; i < carList.size(); i++) {
            if (carList.get(i).getClass().getSimpleName().equals("Sport")) {
                System.out.println("\n" + carList.get(i).toString());
                tools.margeTop(120);
            }
        }
        System.out.println();
    }

    public void deleteSport(Statement statement,ArrayList<Car> carList) throws SQLException {
        System.out.println("Enter a registration number to delete its car information");
        String answer = userInput.next();
        statement.execute("DELETE FROM car_table WHERE registration_number = '"+answer+"'");
        statement.execute("DELETE FROM sport_cars WHERE registration_number = '"+answer+"'");
        for (int i = 0; i < carList.size()-1; i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(answer));
            carList.remove(i).getRegistrationNumber().equalsIgnoreCase(answer);
        }
    }
}
