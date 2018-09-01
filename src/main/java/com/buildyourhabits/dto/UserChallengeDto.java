package com.buildyourhabits.dto;

public class UserChallengeDto {

    private String name;

    public UserChallengeDto(String name) {
        this.name = name;
    }

    public UserChallengeDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "UserChallengeDto{" + "name='" + name + '\'' + '}';
    }
}
