package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static String url;
    private static String user;
    private static String password;
    private static Connection connection = null;

    private DBManager(){

    }

    public static Connection getConnection() {
        if(connection == null)
        url = System.getenv("url");
        user = System.getenv("user");
        password = System.getenv("password");
        try {
            connection = DriverManager.getConnection(url,user, password);
            System.out.println();
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
