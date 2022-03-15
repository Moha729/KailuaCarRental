package main;

import controller.ConsoleController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        System.out.printf("@%-9s %-25s \n@%-9s %-25s \n@%-9s %-25s",
                "Mardin", "mard0012@stud.kea.dk",
                "Yousef", "yous1321@stud.kea.dk",
                "Mohammad", "moha729g@stud.kea.dk");

        new ConsoleController().run();

    }
}
