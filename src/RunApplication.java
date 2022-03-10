import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RunApplication {

     final Scanner userInput = new Scanner(System.in);
     Connection connection = DBManager.getConnection();
     ArrayList<Car> carList = new ArrayList<>();

     boolean running = true;

     public void run() throws SQLException {
        Statement statement = connection.createStatement();
        while (running) {

            System.out.println("Enter 1 to add a car. 2 to ");
            switch (userInput.nextInt()) {
                  case 1 -> addCarToDatabase(statement);
                  case 2 -> show(statement);
//                case 3 ->
//                case 4 ->
//                case 5 ->
//                case 6 ->
//                case 7 ->
                default -> {
                    if (running) {
                        System.out.println("Enter a valid number");
                    }
                }
            }
        }
    }

    public void addCarToDatabase(Statement statement) {

        try {
//            String table = userInput.next();
            String registrationNumber = userInput.next();
            String brand = userInput.next();
            String model = userInput.next();
            String registrationDate = userInput.next();
            int kmDriven = userInput.nextInt();

            boolean ccm = userInput.nextBoolean();
            boolean gear = userInput.nextBoolean();
            boolean cruiseControl = userInput.nextBoolean();
            boolean leatherSeats = userInput.nextBoolean();
            Luxury luxuryCar = new Luxury(registrationNumber,brand,model,registrationDate,kmDriven,ccm,gear,cruiseControl,leatherSeats);
            carList.add(luxuryCar);

            statement.execute("INSERT INTO cars " + "(registration_number,brand,model, registration_date, kmDriven)" + "" +
                    "VALUES('"
                         + luxuryCar.getRegistrationNumber()   + "','"
                         + luxuryCar.getBrand()                +  "','"
                         + luxuryCar.getModel()                + "','"
                         + luxuryCar.getRegistrationDate()     + "','"
                         + luxuryCar.getKmDriven()             + "')");

            statement.execute("INSERT INTO  luxury " + "(registration_number, ccm,gear,cruise_control, leather_seats)" + "" +
                    "VALUES('"
                    + luxuryCar.getRegistrationNumber() + "','"
                    + luxuryCar.isOver3000CCM()         + "','"
                    + luxuryCar.isAutomaticGear()       +  "','"
                    + luxuryCar.isCruiseControl()       + "','"
                    + luxuryCar.isLeatherSeats()        + "')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void show(Statement statement) throws SQLException {
         String name = userInput.next();

        statement.execute("SELECT * FROM cars WHERE registration_number = '"+ name+"'");
    }
}
