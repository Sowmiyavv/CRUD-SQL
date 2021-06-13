package com.company;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class Database
{
    private static Connection con;

    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DBConnecting....");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "sowmi", "1234");
            System.out.println("Database Connected");
        }
        catch (SQLException | ClassNotFoundException e)
        {
            System.out.println("Error : " + e);
        }
        return con;
    }
}
