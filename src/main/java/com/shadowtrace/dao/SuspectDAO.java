
package com.shadowtrace.dao;

import com.shadowtrace.database.DatabaseConnection;
import com.shadowtrace.model.Suspect;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SuspectDAO {


public boolean addSuspect(Suspect suspect) {

    String query = "INSERT INTO suspects "
            + "(case_id, name, age, occupation, description, is_guilty) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, suspect.getCaseId());
        statement.setString(2, suspect.getName());
        statement.setInt(3, suspect.getAge());
        statement.setString(4, suspect.getOccupation());
        statement.setString(5, suspect.getDescription());
        statement.setBoolean(6, suspect.isGuilty());

        int rowsInserted = statement.executeUpdate();

        return rowsInserted > 0;

    } catch (Exception e) {

        System.out.println("Adding suspect failed: " + e.getMessage());
        return false;
    }
}


public java.util.List<Suspect> getSuspectsByCaseId(int caseId) {

    java.util.List<Suspect> suspects = new java.util.ArrayList<>();

    String query = "SELECT * FROM suspects WHERE case_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, caseId);

        try (java.sql.ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Suspect suspect = new Suspect(
                        result.getInt("suspect_id"),
                        result.getInt("case_id"),
                        result.getString("name"),
                        result.getInt("age"),
                        result.getString("occupation"),
                        result.getString("description"),
                        result.getBoolean("is_guilty")
                );

                suspects.add(suspect);
            }
        }

    } catch (Exception e) {

        System.out.println("Fetching suspects failed: " + e.getMessage());
    }

    return suspects;
}


}
