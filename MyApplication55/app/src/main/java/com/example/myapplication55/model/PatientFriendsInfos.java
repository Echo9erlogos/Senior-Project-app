package com.example.myapplication55.model;

public class PatientFriendsInfos {

    private String therapistname;
    public PatientFriendsInfos(){}

    public PatientFriendsInfos(String therapistname){
        this.therapistname=therapistname;
    }
    public String getTherapistname(){
        return therapistname;
    }
    public void setTherapistname(String therapistname){
        this.therapistname=therapistname;
    }
}
