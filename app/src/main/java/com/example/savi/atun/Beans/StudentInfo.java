package com.example.savi.atun.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Savi on 20-03-2016.
 */
public class StudentInfo implements Parcelable {
    int rollNo ;
    String name ;
    boolean isPresent ;

    public StudentInfo(int rollNo, String name, boolean isPresent) {
        this.rollNo = rollNo;
        this.name = name;
        this.isPresent = isPresent;
    }

    protected StudentInfo(Parcel in) {
        rollNo = in.readInt();
        name = in.readString();
        isPresent = in.readByte() != 0;
    }

    public static final Creator<StudentInfo> CREATOR = new Creator<StudentInfo>() {
        @Override
        public StudentInfo createFromParcel(Parcel in) {
            return new StudentInfo(in);
        }

        @Override
        public StudentInfo[] newArray(int size) {
            return new StudentInfo[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(rollNo);
        dest.writeString(name);
        dest.writeByte((byte) (isPresent?1:0));
    }
}
