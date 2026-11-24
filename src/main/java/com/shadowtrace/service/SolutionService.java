
package com.shadowtrace.service;

import com.shadowtrace.dao.SolutionDAO;
import com.shadowtrace.model.Solution;
import com.shadowtrace.model.Case;

public class SolutionService {

    private SolutionDAO solutionDAO = new SolutionDAO();

    // SUBMIT SOLUTION
    public String submitSolution(int caseId, int userId,
                                 String submittedAnswer) {

        if (caseId <= 0 || userId <= 0) {
            return "Invalid Case ID or User ID!";
        }

        if (submittedAnswer == null || submittedAnswer.trim().isEmpty()) {
            return "Answer cannot be empty!";
        }

        CaseService caseService = new CaseService();

        Case mysteryCase = caseService.getCaseById(caseId);

        if (mysteryCase == null) {
            return "Case not found!";
        }

        String correctAnswer = mysteryCase.getCorrectAnswer();

        if (correctAnswer == null || correctAnswer.trim().isEmpty()) {
            return "Correct answer is not configured for this case!";
        }

        boolean isCorrect = submittedAnswer.trim()
                .equalsIgnoreCase(correctAnswer.trim());

        Solution solution = new Solution(
                0,
                caseId,
                userId,
                submittedAnswer.trim(),
                isCorrect,
                null
        );

        boolean submitted = solutionDAO.submitSolution(solution);

        if (!submitted) {
            return "Failed to submit solution!";
        }

        if (isCorrect) {

            caseService.updateCaseStatus(caseId, "SOLVED");

            return "Correct Answer! Mystery Solved!";
        }

        return "Incorrect Answer! Keep investigating!";
    }

    // FETCH SOLUTIONS BY CASE ID
    public java.util.List<Solution> getSolutionsByCaseId(int caseId) {

        if (caseId <= 0) {
            return new java.util.ArrayList<>();
        }

        CaseService caseService = new CaseService();

        if (caseService.getCaseById(caseId) == null) {
            return new java.util.ArrayList<>();
        }

        return solutionDAO.getSolutionsByCaseId(caseId);
    }
}
