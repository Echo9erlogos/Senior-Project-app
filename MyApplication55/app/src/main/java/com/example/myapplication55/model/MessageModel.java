package com.example.myapplication55.model;

public class MessageModel {

    private String sender;
    private String receiver;
    private String message;

    public MessageModel(String sender, String receiver, String message){
        this.message=message;
        this.sender=sender;
        this.receiver=receiver;
    }

    public MessageModel(){}

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
