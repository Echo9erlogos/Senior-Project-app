package com.example.myapplication55.model;

public class appointmentinfodisplayPatient {
    private String therapistname;
    private String state;
    private String therapistuid;
    public appointmentinfodisplayPatient(){}
    public appointmentinfodisplayPatient(String name, String state){
        this.therapistname=name;
        this.state=state;
    }
    public String getTherapistuid(){return therapistuid;}
    public String getTherapistname(){return therapistname;}
    public String getState(){return state;}
    public void setTherapistuid(String therapistuid){this.therapistuid=therapistuid;}
    public void setState(String state){this.state=state;}
    public void setTherapistname(String name){this.therapistname=name;}
}
