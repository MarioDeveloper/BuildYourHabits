package com.everydayhabits.product.module.web.dto;

public class NotificationDto {

        private String firstName;
        private String lastName;
        private int experiencePoint;
        private String time;

        public NotificationDto() {
        }

        public NotificationDto(String firstName, String lastName, int experiencePoint, String time) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.experiencePoint = experiencePoint;
            this.time = time;
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

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getExperiencePoint() {
            return experiencePoint;
        }

        public void setExperiencePoint(int experiencePoint) {
            this.experiencePoint = experiencePoint;
        }

        @Override
        public String toString() {
            return "Notification{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", experiencePoint='" + experiencePoint + '\'' + ", time='" + time + '\'' + '}';
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

