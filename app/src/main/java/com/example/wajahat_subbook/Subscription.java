package com.example.wajahat_subbook;

import java.util.Date;

/**
 * Created by sameerah on 27/01/18.
 */

public abstract class Subscription implements Subscribe {
    private String subName;
    private Date date;
    private Float subCharge;
    private String comment;


    Subscription(String subName, Date date, Float subCharge){
        this.subName = subName;
        this.date = date;
        this.subCharge = subCharge;
        this.comment = null;
    }

    Subscription(String subName, Date date, Float subCharge, String comment) {
        this.subName = subName;
        this.date = date;
        this.subCharge = subCharge;
        this.comment = comment;
    }


    public String getSubName(){
        return subName;
    }

    public void setSubName(String subName) throws NameTooLongException{
        if (subName.length() < 21){
            this.subName = subName;
        }
        else{
            throw new NameTooLongException();
        }
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment) throws CommentTooLongException{
        if (comment.length() < 31){
            this.comment = comment;
        }
        else{
            throw new CommentTooLongException();
        }
    }


    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Float getSubCharge(){
        return subCharge;
    }

    public void setSubCharge(){
        this.subCharge = subCharge;
    }

    public abstract Boolean isGeneral();


}
