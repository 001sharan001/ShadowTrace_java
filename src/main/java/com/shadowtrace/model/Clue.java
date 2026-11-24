
package com.shadowtrace.model;

import java.time.LocalDateTime;

public class Clue {

    private int clueId;
    private int caseId;
    private String clueTitle;
    private String clueDescription;
    private boolean hidden;
    private LocalDateTime createdAt;

    public Clue(int clueId, int caseId,
                String clueTitle, String clueDescription,
                boolean hidden, LocalDateTime createdAt) {

        this.clueId = clueId;
        this.caseId = caseId;
        this.clueTitle = clueTitle;
        this.clueDescription = clueDescription;
        this.hidden = hidden;
        this.createdAt = createdAt;
    }

    public int getClueId() {
        return clueId;
    }

    public int getCaseId() {
        return caseId;
    }

    public String getClueTitle() {
        return clueTitle;
    }

    public String getClueDescription() {
        return clueDescription;
    }

    public boolean isHidden() {
        return hidden;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setClueId(int clueId) {
        this.clueId = clueId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setClueTitle(String clueTitle) {
        this.clueTitle = clueTitle;
    }

    public void setClueDescription(String clueDescription) {
        this.clueDescription = clueDescription;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
