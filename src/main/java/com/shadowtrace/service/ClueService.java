
package com.shadowtrace.service;

import com.shadowtrace.dao.ClueDAO;
import com.shadowtrace.model.Clue;

public class ClueService {

    private ClueDAO clueDAO = new ClueDAO();

public String addClue(int caseId, String clueTitle,
                      String clueDescription, boolean hidden) {

    if (caseId <= 0) {
        return "Invalid Case ID!";
    }

    if (clueTitle == null || clueTitle.trim().isEmpty()
            || clueDescription == null
            || clueDescription.trim().isEmpty()) {
        return "All clue details are required!";
    }

    CaseService caseService = new CaseService();

    if (caseService.getCaseById(caseId) == null) {
        return "Case not found!";
    }

    Clue clue = new Clue(
            0,
            caseId,
            clueTitle.trim(),
            clueDescription.trim(),
            hidden,
            null
    );

    boolean added = clueDAO.addClue(clue);

    if (added) {
        return "Clue added successfully!";
    }

    return "Failed to add clue!";
}

public java.util.List<Clue> getCluesByCaseId(int caseId) {

    if (caseId <= 0) {
        return new java.util.ArrayList<>();
    }

    CaseService caseService = new CaseService();

    if (caseService.getCaseById(caseId) == null) {
        return new java.util.ArrayList<>();
    }

    return clueDAO.getCluesByCaseId(caseId);
}


}
