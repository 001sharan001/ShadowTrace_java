
package com.shadowtrace.dao;

import com.shadowtrace.database.DatabaseConnection;
import com.shadowtrace.model.Clue;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ClueDAO {

public boolean addClue(Clue clue) {

    String query = "INSERT INTO clues "
            + "(case_id, clue_title, clue_description, is_hidden) "
            + "VALUES (?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, clue.getCaseId());
        statement.setString(2, clue.getClueTitle());
        statement.setString(3, clue.getClueDescription());
        statement.setBoolean(4, clue.isHidden());

        int rowsInserted = statement.executeUpdate();

        return rowsInserted > 0;

    } catch (Exception e) {

        System.out.println("Adding clue failed: " + e.getMessage());
        return false;
    }
}

public java.util.List<Clue> getCluesByCaseId(int caseId) {

    java.util.List<Clue> clues = new java.util.ArrayList<>();

    String query = "SELECT * FROM clues WHERE case_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, caseId);

        try (java.sql.ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Clue clue = new Clue(
                        result.getInt("clue_id"),
                        result.getInt("case_id"),
                        result.getString("clue_title"),
                        result.getString("clue_description"),
                        result.getBoolean("is_hidden"),
                        result.getTimestamp("created_at").toLocalDateTime()
                );

                clues.add(clue);
            }
        }

    } catch (Exception e) {

        System.out.println("Fetching clues failed: " + e.getMessage());
    }

    return clues;
}

}
