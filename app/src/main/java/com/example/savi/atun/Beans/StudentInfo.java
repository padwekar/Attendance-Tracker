package com.example.savi.atun.Beans;

/**
 * Created by Savi on 20-03-2016.
 */
public class StudentInfo {
    int rollNo ;
    String name ;
    boolean isPresent ;

    public StudentInfo(int rollNo, String name, boolean isPresent) {
        this.rollNo = rollNo;
        this.name = name;
        this.isPresent = isPresent;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setIsPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }
}
