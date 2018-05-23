package com.everydayhabits.product.module.web.dto;

import java.util.Date;

public class ChallengeEventDto {

    private int id;

    private String title;

    private String description;

    private String difficultyLevel;

    private Date plannedDate;

    private int opponentId;

    private String firstName;

    private String lastName;

    private Boolean isConfirmed;

    private int userInitiatorId;

    public ChallengeEventDto() {
    }

    public ChallengeEventDto(int id, String title, String description, String difficultyLevel, Date plannedDate, String firstName, String lastName, Boolean isConfirmed, int userInitiatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.plannedDate = plannedDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isConfirmed = isConfirmed;
        this.userInitiatorId = userInitiatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Date getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(Date plannedDate) {
        this.plannedDate = plannedDate;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }

    public int getUserInitiatorId() {
        return userInitiatorId;
    }

    public void setUserInitiatorId(int userInitiatorId) {
        this.userInitiatorId = userInitiatorId;
    }

    @Override
    public String toString() {
        return "ChallengeEventDto{" + "title='" + title + '\'' + ", description='" + description + '\'' + ", difficultyLevel='" + difficultyLevel + '\'' + ", plannedDate=" + plannedDate + ", opponentId="  + '}';
    }
}
