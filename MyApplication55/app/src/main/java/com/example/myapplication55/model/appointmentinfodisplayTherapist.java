package com.example.myapplication55.model;

public class appointmentinfodisplayTherapist {
    private String patientuid;
    private String patientname;
    private String address;
    private String date;
    private String time;
    private String phone;
    private String email;
    private String condition;
    private String paymentstatus;
    private String paymentamount;
    public appointmentinfodisplayTherapist(){}

    public appointmentinfodisplayTherapist(String patientuid, String patientname, String address, String date, String time, String phone, String email, String condition, String status, String amount) {
        this.patientuid = patientuid;
        this.patientname = patientname;
        this.address = address;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.email = email;
        this.condition = condition;
        this.paymentstatus = status;
        this.paymentamount = amount;
    }

    public String getPatientuid() {
        return patientuid;
    }

    public void setPatientuid(String patientuid) {
        this.patientuid = patientuid;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getpaymentstatus() {
        return paymentstatus;
    }

    public void setpaymentstatus(String paymentstatus) {
        this.paymentstatus = paymentstatus;
    }

    public String getpaymentamount() {
        return paymentamount;
    }

    public void setpaymentamount(String paymentamount) {
        this.paymentamount = paymentamount;
    }
}
