package com.example.backend;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CsvLoader {

    public static void loadUsers(String filePath) {
        try (
            Connection conn = DatabaseConnector.connect();
            CSVReader reader = new CSVReader(new FileReader(filePath));
        ) {
            String[] nextLine;
            reader.readNext(); // skip header

            String sql = "INSERT INTO users (id, first_name, last_name, email, age, gender, state, street_address, postal_code, city, country, latitude, longitude, traffic_source, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            while ((nextLine = reader.readNext()) != null) {
                for (int i = 0; i < nextLine.length; i++) {
                    pstmt.setString(i + 1, nextLine[i]);
                }
                pstmt.executeUpdate();
            }

            System.out.println("Users loaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
