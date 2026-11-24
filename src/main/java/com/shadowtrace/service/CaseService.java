
package com.shadowtrace.service;

import com.shadowtrace.dao.CaseDAO;
import com.shadowtrace.model.Case;

public class CaseService {

    private CaseDAO caseDAO = new CaseDAO();

    // CREATE NEW CASE
    public String createCase(String title, String description,
                             String difficulty, String correctAnswer) {

        if (title == null || title.trim().isEmpty()
                || description == null || description.trim().isEmpty()) {

            return "Title and description are required!";
        }

        if (difficulty == null || difficulty.trim().isEmpty()) {
            return "Difficulty is required!";
        }

        if (correctAnswer == null || correctAnswer.trim().isEmpty()) {
            return "Correct answer is required!";
        }

        title = title.trim();
        description = description.trim();
        difficulty = difficulty.trim();
        correctAnswer = correctAnswer.trim();

        if (!difficulty.equalsIgnoreCase("Easy")
                && !difficulty.equalsIgnoreCase("Medium")
                && !difficulty.equalsIgnoreCase("Hard")) {

            return "Invalid difficulty! Choose Easy, Medium, or Hard.";
        }

        Case mysteryCase = new Case(
                0,
                title,
                description,
                difficulty.toUpperCase(),
                "OPEN",
                correctAnswer,
                null
        );

        boolean created = caseDAO.createCase(mysteryCase);

        if (created) {
            return "Mystery case created successfully!";
        }

        return "Case creation failed!";
    }

    // FETCH ALL CASES
    public java.util.List<Case> getAllCases() {

        return caseDAO.getAllCases();
    }

    // FETCH CASE BY ID
    public Case getCaseById(int caseId) {

        if (caseId <= 0) {
            return null;
        }

        return caseDAO.getCaseById(caseId);
    }

    // UPDATE CASE STATUS
    public String updateCaseStatus(int caseId, String status) {

        if (caseId <= 0) {
            return "Invalid case ID!";
        }

        if (status == null) {
            return "Status is required!";
        }

        status = status.trim();

        if (!status.equalsIgnoreCase("OPEN")
                && !status.equalsIgnoreCase("IN_PROGRESS")
                && !status.equalsIgnoreCase("SOLVED")) {

            return "Invalid status! Choose OPEN, IN_PROGRESS, or SOLVED.";
        }

        boolean updated = caseDAO.updateCaseStatus(
                caseId, status.toUpperCase()
        );

        if (updated) {
            return "Case status updated successfully!";
        }

        return "Case not found or update failed!";
    }
}
