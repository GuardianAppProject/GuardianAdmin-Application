package com.guardian.guardianadmin_v1.UserModels;

import com.guardian.guardianadmin_v1.MainActivity;
import com.guardian.guardianadmin_v1.Transmissions.AllPhoneGetter;
import com.guardian.guardianadmin_v1.Transmissions.SingleUserWorker;

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

        updatePhoneNumbers();
        //inja miaim user haro migirim
        if(allUsers.isEmpty()) {
            new UserList("ali", "0912433434", 45, 120);
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

    public static void updatePhoneNumbers(){
        ArrayList<String> allPhoneNumbers;
        AllPhoneGetter.updateData(MainActivity.getToken());
        allPhoneNumbers = AllPhoneGetter.getAllPhoneNumbers();
        try{
            sleep(100);
        }catch (Exception e){

        }
        System.out.println(allPhoneNumbers);
        for(String number : allPhoneNumbers){
            SingleUserWorker.getUserByNum(MainActivity.getToken(),number);
            try {
                sleep(300);
            }catch (Exception e){

            }
            String[] user = SingleUserWorker.getUserByNum(MainActivity.getToken(),number);
            System.out.println(Arrays.toString(user));
            if(user == null)
                continue;
            if(user.length != 4)
                continue;
            new UserList(user[1],user[0],Double.parseDouble(user[3]),Double.parseDouble(user[2]));
        }
    }


}
