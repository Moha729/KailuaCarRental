package main;

import controller.ConsoleController;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        new ConsoleController().run();
    }
}
