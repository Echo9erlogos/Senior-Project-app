package com.example.myapplication55.model;

public class appointmentinfodisplayPatient {
    private String therapistname;
    private String state;
    public appointmentinfodisplayPatient(){}
    public appointmentinfodisplayPatient(String name, String state){
        this.therapistname=name;
        this.state=state;
    }
    public String getName(){return therapistname;}
    public String getState(){return state;}
    public void setState(String state){this.state=state;}
    public void setName(String name){this.therapistname=name;}
}
