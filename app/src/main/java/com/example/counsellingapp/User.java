package com.example.counsellingapp;

public class User {

    public String name, usn, batch, email, phone, gender, dob,sslcyop, sslcpercent,sslcschool, puyop, pupercent, puclg, ugdegree,ugyop,ugcgpa,ugcombination,ugclg;


    public User(){

    }

    public User(String name, String usn, String batch, String email, String phone, String gender, String dob, String sslcyop,  String sslcpercent, String sslcschool,
                String puyop, String pupercent, String puclg, String ugdegree, String ugyop, String ugcgpa, String ugcombination, String ugclg   ){
        this.name = name;
        this.usn = usn;
        this.batch = batch;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.dob = dob;
        this.sslcyop = sslcyop;
        this.sslcpercent = sslcpercent;
        this.sslcschool = sslcschool;
        this.puyop = puyop;
        this.pupercent = pupercent;
        this.puclg = puclg;
        this.ugdegree = ugdegree;
        this.ugyop = ugyop;
        this.ugcgpa = ugcgpa;
        this.ugcombination = ugcombination;
        this.ugclg = ugclg;


    }
}
