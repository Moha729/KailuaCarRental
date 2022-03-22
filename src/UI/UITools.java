package UI;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UITools {

    public static final String red = "\u001B[31m";
    Scanner userInput = new Scanner(System.in);

    public static String centerString(int width, String s) {
        return String.format("|%-" + width + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s)) + "|";
    }

    public String returnStringInfo(int width, int height, String text) {
        customizedButton(width, height, text);
        String info = userInput.next();
        return info;
    }

    public int returnIntInfo(int width, int height, String text) {
        customizedButton(width, height, text);
        int info = userInput.nextInt();
        return info;
    }

    public void customizedButton(int width, int height, String text) {
        margeTop(width);
        System.out.println(red);
        margingCentre(height, width, text);
        margeTop(width);

    }

    public void margingCentre(int height, int width, String text) {

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

    public void margeTop(int width) {
        System.out.print(red + " ");
        for (int i = 0; i <= width - 1; i++)
            System.out.print("_");
    }

    public String doubleButton(String text1, String text2) {
        String margeTop = " ___________________________________________________________ ";
        String middleSpace = "|                                                           |";
        String margeCenter = centerString(59, text1);
        String margeCenter2 = centerString(59, text2);
        String margeBottom = " ___________________________________________________________ ";
        return ("\n" + margeTop + margeTop + "\n" + middleSpace + middleSpace + "\n" + margeCenter + margeCenter2 + "\n" + middleSpace + middleSpace + "\n" + margeBottom + margeBottom);
    }

    //Ikke pille mere her uden at give besked!
    public int menuOptions(String menuOrder) {
        if (menuOrder.equalsIgnoreCase("menuOptions")) {
            return menuOptions("Welcome to Kailua car rental",
                    ">1< Rentals", ">2< Cars", ">3< Customers", ">4< Exit");
        }
        if (menuOrder.equalsIgnoreCase("carMenuOptions")) {
            whiteSpace(10);
            return menuOptions("Cars", ">1< See cars", ">2< Update car",
                    ">3< New car", ">4< Delete car");
        }
        if (menuOrder.equalsIgnoreCase("customerMenuOptions")) {
            whiteSpace(10);
            return menuOptions("Customers", ">1< See customers", ">2< Update a customer",
                    ">3< Create a new customer", ">4< Delete a customer");
        }
        if (menuOrder.equalsIgnoreCase("rentalMenuOptions")) {
            whiteSpace(10);
            return menuOptions("Rentals", ">1< New rental", ">2< Active rentals",
                    ">3< Change rental", ">4< End rental");
        }
        return 0;
    }

    private int menuOptions(String headText, String menu1, String menu2, String menu3, String menu4) {
        customizedButton(120, 7, headText);
        System.out.print(doubleButton(menu1, menu2));
        System.out.print(doubleButton(menu3, menu4));
        return userInput.nextInt();
    }

    public boolean continueButton() {
        customizedButton(15, 1, ">1< continue..");
        System.out.print(" ");
        int start = userInput.nextInt();
        if (start != 0) {
            whiteSpace(10);
            return true;
        }
        return false;
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

    public void whiteSpace(int j) {
        for (int i = 0; i < j; i++)
            System.out.println();
    }
}