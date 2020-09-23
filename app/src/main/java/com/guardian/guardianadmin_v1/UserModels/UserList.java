package com.guardian.guardianadmin_v1.UserModels;

import com.guardian.guardianadmin_v1.MainActivity;
import com.guardian.guardianadmin_v1.Transmissions.AllPhoneGetter;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class UserList {

    private String imageURL;
    private String name;
    private String phoneNumber;
    private double average;
    private double speed;

    private static ArrayList<UserList> allUsers = new ArrayList<>();

    private static ArrayList<String> allPhoneNumbers;

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
        allPhoneNumbers = new ArrayList<>();
        updatePhoneNumbers();
        //inja miaim user haro migirim
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

    private static void updatePhoneNumbers(){
        AllPhoneGetter.updateData(MainActivity.getToken());
        try{
            sleep(500);
        }catch (Exception e){

        }
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");
        System.out.println(allPhoneNumbers);
    }

    public static void setAllPhoneNumbers(ArrayList<String> allPhoneNumbers) {
        UserList.allPhoneNumbers = allPhoneNumbers;
    }

}
