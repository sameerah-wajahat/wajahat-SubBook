/* Copyright (c) 2018 Sameerah Wajahat, CMPUT 301, University of Alberta - All Rights Reserved.
* You may use, distribute or modify this code under terms and conditions of Code of Student Behaviour at
* University of Alberta.
* You can find a copy of the license in this project. Otherwise please contact wajahat@ualberta.ca
*/


package com.example.wajahat_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author Sameerah Wajahat
 * This controls what happens on the home screen which consists of two buttons - Add and View
 */

public class SubBook extends AppCompatActivity {

    /**
     * Displays the home screen activity which has two buttons on - add and view
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book);
    }

    /**
     * Launches the Add page activity which allows the user to create a new subscription
     * @param view1
     */

    public void openAddScreen(View view1){
        Intent intent1 = new Intent(this, AddSub.class);
        startActivity(intent1);
    }

    /**
     * Launches the view page activity which displays the list of subscriptions
     * @param view2
     */

    public void openViewScreen(View view2){
        Intent intent2 = new Intent(this, ViewSub.class);
        startActivity(intent2);
    }
}
