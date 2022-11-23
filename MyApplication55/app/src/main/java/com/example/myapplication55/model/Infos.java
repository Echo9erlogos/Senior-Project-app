package com.example.myapplication55.model;

public class Infos {
    private String name;
    private String city;
    private String score;
    public Infos(){}

    public Infos(String name, String city, String score){
        this.name=name;
        this.city=city;
        this.score=score;
    }
    public String getName(){
        return name;
    }
    public String getCity(){
        return city;
    }
    public String getScore(){
        return score;
    }
    public void setName1(String name){
        this.name=name;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setScore(String score){
        this.score=score;
    }
}
