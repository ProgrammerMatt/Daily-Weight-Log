package com.example.mattwilfert.dailyweightlog;

/**
 * Created by MattWilfert on 4/3/15.
 */
public class Weight {
    private long id;
    private double weight;
    private String date;


    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

}
