

package com.shadowtrace.service;

import com.shadowtrace.dao.SuspectDAO;
import com.shadowtrace.model.Suspect;

public class SuspectService {

    private SuspectDAO suspectDAO = new SuspectDAO();


public String addSuspect(int caseId, String name, int age,
                         String occupation, String description) {

    if (caseId <= 0) {
        return "Invalid Case ID!";
    }

    if (name == null || name.trim().isEmpty()
            || occupation == null || occupation.trim().isEmpty()
            || description == null || description.trim().isEmpty()) {
        return "All suspect details are required!";
    }

    if (age <= 0 || age > 120) {
        return "Invalid age!";
    }

    CaseService caseService = new CaseService();

    if (caseService.getCaseById(caseId) == null) {
        return "Case not found!";
    }

    Suspect suspect = new Suspect(
            0,
            caseId,
            name.trim(),
            age,
            occupation.trim(),
            description.trim(),
            false
    );

    boolean added = suspectDAO.addSuspect(suspect);

    if (added) {
        return "Suspect added successfully!";
    }

    return "Failed to add suspect!";
}


public java.util.List<Suspect> getSuspectsByCaseId(int caseId) {

    if (caseId <= 0) {
        return new java.util.ArrayList<>();
    }

    CaseService caseService = new CaseService();

    if (caseService.getCaseById(caseId) == null) {
        return new java.util.ArrayList<>();
    }

    return suspectDAO.getSuspectsByCaseId(caseId);
}


}
