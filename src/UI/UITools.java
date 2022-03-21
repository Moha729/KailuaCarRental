package UI;

import repository.CarRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UITools {

    Scanner userInput = new Scanner(System.in);
    public static final String red = "\u001B[31m";

    public String returnStringInfo(int width, int height, String text){
        customizedButton(width, height, text);
        String info = userInput.next();
        return info;
    }
    public int returnIntInfo(int width, int height, String text){
        customizedButton(width, height, text);
        int info = userInput.nextInt();
        return info;
    }

    public void customizedButton(int width, int height, String text){
        margeTop(width);
        System.out.println(red);
        margingCentre(height, width, text);
        margeTop(width);

    }
    public void margingCentre(int height, int width, String text1, String text2) {

        margeMiddlSpace(height, width);
        System.out.print(centerString(width / 2, text1));
        System.out.println(centerString(width / 2, text2));
        margeMiddlSpace(height, width );
    }

    public void margingCentre(int height, int width, String text){

        margeMiddlSpace(height, width);
        System.out.println(centerString(width, text));
        margeMiddlSpace(height, width);
    }

    public void margeMiddlSpace(int height, int width) {
        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width; j++) {
                System.out.print(" ");
            }
            System.out.println("|");
        }
    }

    public void margeTop(int width){
        System.out.print(red+" ");
        for (int i = 0; i <= width - 1; i++)
            System.out.print("_");
    }

    public String welcomeText(String INTROTEXT) {
        String BOX1_1 = " ------------------------------------------------------------------------------------------------";
        String BOX1_2 = "|                                                                                                |";
        String centerText = centerString(96, INTROTEXT);

        return BOX1_1 + "\n" + BOX1_2 + "\n" + centerText + "\n" + BOX1_2 + "\n" + BOX1_1;
    }//return String.format("%s\n%s\n|\t\t\t\t\t\t\t\t%35s\t\t\t\t\t\t\t\t |\n%s\n%s\n", BOX1_1, BOX1_2, INTROTEXT, BOX1_2, BOX1_1);

    public static String centerString(int width, String s) {
        return String.format("|%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s)) + "|";
    }

    public String doubleButton(String text1, String text2) {
        String margeTop    = " ___________________________________________________________ ";
        String middleSpace = "|                                                           |";
        String margeCenter = centerString(59, text1);
        String margeCenter2 = centerString(59, text2);
        String margeBottom = " ___________________________________________________________ ";
        return ("\n" + margeTop + margeTop + "\n" + middleSpace + middleSpace + "\n" + margeCenter + margeCenter2 + "\n" + middleSpace + middleSpace + "\n" + margeBottom + margeBottom);
    }

    public void doubleBottom(int width, int height, String text1, String text2) {

        margeTop(width);
        System.out.println();
        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
        System.out.print(centerString(width / 2, text1));
        System.out.print(centerString(width / 2, text2));
        System.out.println();

        for (int i = 0; i < height / 2; i++) {
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
            for (int j = 1; j <= width / 2; j++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
    }

    public int menuOptions(){
        customizedButton(120, 7, "Welcome to Kailua car rental");
        System.out.print(doubleButton(">1< Rentals", ">2< Cars"));
        System.out.print(doubleButton(">3< Customers", ">4< Exit"));
        return userInput.nextInt();
    }

    public void carMenuOptions(){
        whiteSpace();
        whiteSpace();
        System.out.println();
        customizedButton(120, 3, "Cars");

        System.out.print(doubleButton(">1< See cars", ">2< Update car"));
        System.out.print(doubleButton(">3< New car", ">4< Delete car"));
    }

    public void customerMenuOptions() {
        whiteSpace();
        whiteSpace();
        System.out.println();
        customizedButton(120, 3, "Customers");
        System.out.print(doubleButton(">1< See customers", ">2< Update a customer"));
        System.out.print(doubleButton(">3< Create a new customer", ">4< Delete a customer"));
    }

    public void rentalMenuOptions() {
        whiteSpace();
        whiteSpace();
        System.out.println();
        customizedButton(120, 3, "Rentals");
        System.out.print(doubleButton(">1< New rental", ">2< Active rentals"));
        System.out.print(doubleButton(">3< Change rental", ">4< End rental"));
    }


    public void closeProgram(Statement statement, Connection connection) {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Closing the application...");
            Thread.sleep(1000);
            customizedButton(120, 1, "System closed");
            System.exit(0);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }

    public void whiteSpace() {
        for (int i = 0; i < 6; i++)
            System.out.println();
    }
}
