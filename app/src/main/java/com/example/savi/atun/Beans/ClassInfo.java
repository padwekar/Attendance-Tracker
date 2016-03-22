package com.example.savi.atun.Beans;

/**
 * Created by Savi on 19-03-2016.
 */
public class ClassInfo {
    String classId , className,section,department,studentListString;
    int strength;

    public ClassInfo(String classId, String className, String section,String department, String studentListStringint,int strength) {
        this.classId = classId;
        this.className = className;
        this.section = section;
        this.department = department;
        this.studentListString = studentListStringint ;
        this.strength = strength;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSection() {
        return section;
    }

    public String getStudentListString() {
        return studentListString;
    }

    public void setStudentListString(String studentListString) {
        this.studentListString = studentListString;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
