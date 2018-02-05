/* Copyright (c) 2018 Sameerah Wajahat, CMPUT 301, University of Alberta - All Rights Reserved.
* You may use, distribute or modify this code under terms and conditions of Code of Student Behaviour at
* University of Alberta.
* You can find a copy of the license in this project. Otherwise please contact wajahat@ualberta.ca
*/

package com.example.wajahat_subbook;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by sameerah on 27/01/18.
 */

/**
 * @author Sameerah Wajahat
 * Represents a subscription
 * @see ViewSub
 */
public class Subscription {
    private String subName;     //Name of the subscription
    private Calendar date;      //Date of the subscription
    private Float subCharge;    //Monthly charge of the subscription
    private String comment;     //Comments about the subscription

    /**
     * Constructs a subscription instance using name, date, charge (with or without the comment)
     * @param subName - name of the subscription
     * @param date - date of the subscription
     * @param subCharge - monthly charge of the subscription
     */

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

    /**
     * Getter for name of the subscription
     * @return subName - name of the subscription
     */

    public String getSubName(){
        return subName;
    }

    public void setSubName(String subName){
        this.subName = subName;
    }

    /**
     * Getter for the name of the subscription
     * @return comment - comment about the subscription
     */

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Used by the adapter to set the appearance of the subscription in ListView
     * @return appearance - format of the attributes of the subscription instance
     */

    public String toString(){
        String appearance = this.subName + "\n" + this.date.get(Calendar.YEAR) + "/" + (this.date.get(Calendar.MONTH)+1) + "/" + this.date.get(Calendar.DAY_OF_MONTH) + "\n" + String.format("$%,.2f", this.subCharge) + "\n";
        return(appearance);
    }

    /**
     * Getter for the date of the subscription
     * @return date - date of the subscription
     */

    public Calendar getDate(){
        return date;
    }

    public void setDate(Calendar date){
        this.date = date;
    }

    /**
     * Getter for the date of the subscription
     * @return - subCharge - monthly charge of the subscription
     */

    public Float getSubCharge(){
        return subCharge;
    }

    public void setSubCharge(Float subCharge){
        this.subCharge = subCharge;
    }


}
