package com.example.myapplication55.model;

public class TherapistFriendsInfos {

    private String patientname;
    public TherapistFriendsInfos(){}

    public TherapistFriendsInfos(String patientname){
        this.patientname=patientname;
    }
    public String getPatientname(){
        return patientname;
    }
    public void setPatientname(String patientname){this.patientname=patientname;}
}
