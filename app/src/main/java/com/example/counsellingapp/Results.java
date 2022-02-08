package com.example.counsellingapp;

public class Results {

    public String usn, semester, test, sub1, sub2, sub3, sub4, sub5;

    public Results(){

    }
    public Results(String usn, String semester, String test, String sub1, String sub2, String sub3, String sub4, String sub5){
        this.usn = usn;
        this.semester = semester;
        this. test = test;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        this.sub4 = sub4;
        this.sub5 = sub5;

    }

    public String getSemester() {
        return semester;
    }

    public String getTest() {
        return test;
    }

    public String getUsn() {
        return usn;
    }

    public String getSub1() {
        return sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public String getSub3() {
        return sub3;
    }

    public String getSub4() {
        return sub4;
    }

    public String getSub5() {
        return sub5;
    }
}
