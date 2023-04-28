package com.example.myapplication55.model;

public class appointmentinfodisplayPatient {
    private String therapistname;
    private String state;
    private String therapistuid;
    private String paymentamount;
    private String weeks;
    private String advice;
    public appointmentinfodisplayPatient(){}
    public appointmentinfodisplayPatient(String name, String state, String paymentamount,String uid,String weeks,String advice){
        this.therapistname=name;
        this.state=state;
        this.paymentamount=paymentamount;
        this.therapistuid=uid;
        this.weeks=weeks;
        this.advice=advice;
    }
    public String getTherapistuid(){return therapistuid;}
    public String getTherapistname(){return therapistname;}
    public String getState(){return state;}
    public String getpaymentamount(){return paymentamount;}
    public String getWeeks(){return weeks;}
    public String getAdvice(){return advice;}
    public void setTherapistuid(String therapistuid){this.therapistuid=therapistuid;}
    public void setState(String state){this.state=state;}
    public void setTherapistname(String name){this.therapistname=name;}
    public void setPaymentamount(String paymentamount){this.paymentamount=paymentamount;}
    public void setWeeks(String weeks){this.weeks=weeks;}
    public void setAdvice(String advice){this.advice=advice;}
}
