
package com.shadowtrace.model;

public class Suspect {

    private int suspectId;
    private int caseId;
    private String name;
    private int age;
    private String occupation;
    private String description;
    private boolean guilty;

    public Suspect(int suspectId, int caseId,
                   String name, int age,
                   String occupation, String description,
                   boolean guilty) {

        this.suspectId = suspectId;
        this.caseId = caseId;
        this.name = name;
        this.age = age;
        this.occupation = occupation;
        this.description = description;
        this.guilty = guilty;
    }

    public int getSuspectId() {
        return suspectId;
    }

    public int getCaseId() {
        return caseId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGuilty() {
        return guilty;
    }

    public void setSuspectId(int suspectId) {
        this.suspectId = suspectId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGuilty(boolean guilty) {
        this.guilty = guilty;
    }
}
