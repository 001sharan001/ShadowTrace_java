
package com.shadowtrace.model;

import java.time.LocalDateTime;

public class Case {

    private int caseId;
    private String title;
    private String description;
    private String difficulty;
    private String status;
    private String correctAnswer;
    private LocalDateTime createdAt;

    public Case(int caseId, String title,
                String description, String difficulty,
                String status, String correctAnswer,
                LocalDateTime createdAt) {

        this.caseId = caseId;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.status = status;
        this.correctAnswer = correctAnswer;
        this.createdAt = createdAt;
    }

    public int getCaseId() {
        return caseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getStatus() {
        return status;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
