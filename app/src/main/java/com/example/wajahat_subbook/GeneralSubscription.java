package com.example.wajahat_subbook;

import java.util.Date;

/**
 * Created by sameerah on 28/01/18.
 */

public class GeneralSubscription extends Subscription {

    GeneralSubscription(String subName, Date date, Float subCharge){
        super(subName, date, subCharge);
    }

    GeneralSubscription(String subName, Date date, Float subCharge, String comment) {
        super(subName, date, subCharge, comment);
    }

    @Override
    public Boolean isGeneral() {
        return Boolean.TRUE;
    }
}
