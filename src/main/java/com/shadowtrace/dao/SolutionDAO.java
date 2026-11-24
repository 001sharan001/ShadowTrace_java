
package com.shadowtrace.dao;

import com.shadowtrace.database.DatabaseConnection;
import com.shadowtrace.model.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SolutionDAO {


public boolean submitSolution(Solution solution) {

    String query = "INSERT INTO solutions "
            + "(case_id, user_id, submitted_answer, is_correct) "
            + "VALUES (?, ?, ?, ?)";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, solution.getCaseId());
        statement.setInt(2, solution.getUserId());
        statement.setString(3, solution.getSubmittedAnswer());
        statement.setBoolean(4, solution.isCorrect());

        int rowsInserted = statement.executeUpdate();

        return rowsInserted > 0;

    } catch (Exception e) {

        System.out.println("Submitting solution failed: " + e.getMessage());
        return false;
    }
}

public java.util.List<Solution> getSolutionsByCaseId(int caseId) {

    java.util.List<Solution> solutions = new java.util.ArrayList<>();

    String query = "SELECT * FROM solutions WHERE case_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {

        statement.setInt(1, caseId);

        try (java.sql.ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Solution solution = new Solution(
                        result.getInt("solution_id"),
                        result.getInt("case_id"),
                        result.getInt("user_id"),
                        result.getString("submitted_answer"),
                        result.getBoolean("is_correct"),
                        result.getTimestamp("submitted_at").toLocalDateTime()
                );

                solutions.add(solution);
            }
        }

    } catch (Exception e) {

        System.out.println("Fetching solutions failed: " + e.getMessage());
    }

    return solutions;
}


}
