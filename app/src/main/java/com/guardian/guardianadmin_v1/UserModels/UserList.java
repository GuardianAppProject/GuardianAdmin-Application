package com.guardian.guardianadmin_v1.UserModels;

import java.util.ArrayList;

public class UserList {

    private String imageURL;
    private String name;
    private String phoneNumber;
    private double average;
    private double speed;

    private static ArrayList<UserList> allUsers = new ArrayList<>();

    public UserList(String name, String phoneNumber, double average, double speed) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setAverage(average);
        setSpeed(speed);

        allUsers.add(this);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setAverage(double average) {
        this.average = average;
    }

    private void setSpeed(double speed) {
        this.speed = speed;
    }

    public static ArrayList<UserList> getAllUsers() {
        if(allUsers.isEmpty()) {
            allUsers.add(new UserList("ali", "0912433434", 45, 120));
            allUsers.add(new UserList("ali2", "0912433434", 45, 120));
            allUsers.add(new UserList("ali3", "0912433434", 45, 120));
            allUsers.add(new UserList("ali4", "0912433434", 45, 120));
            allUsers.add(new UserList("ali5", "0912433434", 45, 120));
        }
        return allUsers;
    }

    public static void setAllUsers(ArrayList<UserList> allUsers) {
        UserList.allUsers = allUsers;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAverage() {
        return average;
    }

    public double getSpeed() {
        return speed;
    }
}
