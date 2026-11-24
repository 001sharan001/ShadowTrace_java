
package com.shadowtrace.model;

import java.time.LocalDateTime;

public class Solution {

    private int solutionId;
    private int caseId;
    private int userId;
    private String submittedAnswer;
    private boolean correct;
    private LocalDateTime submittedAt;

    public Solution(int solutionId, int caseId,
                    int userId, String submittedAnswer,
                    boolean correct, LocalDateTime submittedAt) {

        this.solutionId = solutionId;
        this.caseId = caseId;
        this.userId = userId;
        this.submittedAnswer = submittedAnswer;
        this.correct = correct;
        this.submittedAt = submittedAt;
    }

    public int getSolutionId() {
        return solutionId;
    }

    public int getCaseId() {
        return caseId;
    }

    public int getUserId() {
        return userId;
    }

    public String getSubmittedAnswer() {
        return submittedAnswer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSolutionId(int solutionId) {
        this.solutionId = solutionId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSubmittedAnswer(String submittedAnswer) {
        this.submittedAnswer = submittedAnswer;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
