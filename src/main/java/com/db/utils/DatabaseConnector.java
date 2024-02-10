package com.db.utils;
import org.postgresql.Driver;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {
    private static final String URL = "jdbc:postgresql://localhost:5432/gestionstock";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() {
        try {
            //Class.forName("postgresql-42.7.1");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexion réussie !");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("La connexion a échoué : " + e.getMessage());
            return null;
        }
    }
}
