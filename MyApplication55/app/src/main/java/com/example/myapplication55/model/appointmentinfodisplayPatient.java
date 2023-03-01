package com.example.myapplication55.model;

public class appointmentinfodisplayPatient {
    private String therapistname;
    private String state;
    private String therapistuid;
    private String paymentamount;
    public appointmentinfodisplayPatient(){}
    public appointmentinfodisplayPatient(String name, String state, String paymentamount){
        this.therapistname=name;
        this.state=state;
        this.paymentamount=paymentamount;
    }
    public String getTherapistuid(){return therapistuid;}
    public String getTherapistname(){return therapistname;}
    public String getState(){return state;}
    public String getpaymentamount(){return paymentamount;}
    public void setTherapistuid(String therapistuid){this.therapistuid=therapistuid;}
    public void setState(String state){this.state=state;}
    public void setTherapistname(String name){this.therapistname=name;}
    public void setpaymentamount(String paymentamount){this.paymentamount=paymentamount;}
}
