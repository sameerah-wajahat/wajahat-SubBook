package com.example.wajahat_subbook;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sameerah on 27/01/18.
 */

public class Subscription {
    private String subName;
    private Calendar date;
    private Float subCharge;
    private String comment;


    Subscription(String subName, Calendar date, Float subCharge){
        this.subName = subName;
        this.date = date;
        this.subCharge = subCharge;
        this.comment = null;
    }

    Subscription(String subName, Calendar date, Float subCharge, String comment) {
        this.subName = subName;
        this.date = date;
        this.subCharge = subCharge;
        this.comment = comment;
    }


    public String getSubName(){
        return subName;
    }

    public void setSubName(String subName){
        this.subName = subName;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String toString(){
        String appearance = this.subName + "\n" + this.date.get(Calendar.YEAR) + "/" + (this.date.get(Calendar.MONTH)+1) + "/" + this.date.get(Calendar.DAY_OF_MONTH) + "\n" + String.format("$%,.2f", this.subCharge) + "\n";
        return(appearance);
    }


    public Calendar getDate(){
        return date;
    }

    public void setDate(Calendar date){
        this.date = date;
    }

    public Float getSubCharge(){
        return subCharge;
    }

    public void setSubCharge(Float subCharge){
        this.subCharge = subCharge;
    }


}
