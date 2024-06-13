package com.example.proje;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection() {
        String databaseName = "kullanici";
        String databaseUser = "root";
        String databasePassword = "password";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
     catch (Exception e) {
             System.err.println("Veritabanı bağlantısı kurulurken bir hata oluştu:");
             e.printStackTrace();

     }

return databaseLink;
}}
