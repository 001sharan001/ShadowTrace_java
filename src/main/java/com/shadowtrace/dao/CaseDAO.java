
package com.shadowtrace.dao;

import com.shadowtrace.database.DatabaseConnection;
import com.shadowtrace.model.Case;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class CaseDAO {

    public boolean createCase(Case mysteryCase) {

        String query = "INSERT INTO cases "
                + "(title, description, difficulty, status, correct_answer) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, mysteryCase.getTitle());
            statement.setString(2, mysteryCase.getDescription());
            statement.setString(3, mysteryCase.getDifficulty());
            statement.setString(4, mysteryCase.getStatus());
            statement.setString(5, mysteryCase.getCorrectAnswer());

            int rowsInserted = statement.executeUpdate();

            return rowsInserted > 0;

        } catch (Exception e) {

            System.out.println("Case creation failed: " + e.getMessage());
            return false;
        }
    }

    public List<Case> getAllCases() {

        List<Case> cases = new ArrayList<>();

        String query = "SELECT * FROM cases";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {

            while (result.next()) {

                Case mysteryCase = new Case(
                        result.getInt("case_id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getString("difficulty"),
                        result.getString("status"),
                        result.getString("correct_answer"),
                        result.getTimestamp("created_at").toLocalDateTime()
                );

                cases.add(mysteryCase);
            }

        } catch (Exception e) {

            System.out.println("Fetching cases failed: " + e.getMessage());
        }

        return cases;
    }

    public Case getCaseById(int caseId) {

        String query = "SELECT * FROM cases WHERE case_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, caseId);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    return new Case(
                            result.getInt("case_id"),
                            result.getString("title"),
                            result.getString("description"),
                            result.getString("difficulty"),
                            result.getString("status"),
                            result.getString("correct_answer"),
                            result.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println("Fetching case details failed: " + e.getMessage());
        }

        return null;
    }

    public boolean updateCaseStatus(int caseId, String status) {

        String query = "UPDATE cases SET status = ? WHERE case_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, status);
            statement.setInt(2, caseId);

            int rowsUpdated = statement.executeUpdate();

            return rowsUpdated > 0;

        } catch (Exception e) {

            System.out.println("Case status update failed: " + e.getMessage());
            return false;
        }
    }
}
