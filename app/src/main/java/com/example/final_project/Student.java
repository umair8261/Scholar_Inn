package com.example.final_project;

import android.widget.Spinner;

public class Student
{
    String Name,Reg,Email,DOB,Age,Gender,FName,Pnumber,Address;

    public Student()
    {
    }

    public String getName() {
        return Name;
    }

    public String getReg() {
        return Reg;
    }

    public String getEmail() {
        return Email;
    }

    public String getDOB() {
        return DOB;
    }

    public String getAge() {
        return Age;
    }

    public String getGender() {
        return Gender;
    }

    public String getFName() {
        return FName;
    }

    public String getPnumber() {
        return Pnumber;
    }

    public String getAddress()
    {
        return Address;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public void setReg(String reg) {
        Reg = reg;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setAge(String age) {
        Age = age;
    }

    public void setGender(Spinner gender)
    {
        Gender = gender.toString();
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public void setPnumber(String pnumber) {
        Pnumber = pnumber;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
