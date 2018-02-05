/* Copyright (c) 2018 Sameerah Wajahat, CMPUT 301, University of Alberta - All Rights Reserved.
* You may use, distribute or modify this code under terms and conditions of Code of Student Behaviour at
* University of Alberta.
* You can find a copy of the license in this project. Otherwise please contact wajahat@ualberta.ca
*/

package com.example.wajahat_subbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Sameerah Wajahat
 * This acitivity allows the user to edit an already existing subscription
 * @see ViewSub
 */

public class EditSub extends AppCompatActivity {

    private EditText nameField;     //Widget that receives the name of the Subscription
    private EditText dateField;     //Widget that receives the date of the Subscription
    private Calendar calDate;       //Date of the subscription
    private EditText chargeField;   //Widget that receives the monthly charge of the subscription
    private EditText commentField;  //Widget that receives the comment about the subscription
    private Integer position;       //Index position of the item being edited in the list

    /**
     * Displays the add screen activity that allows the user to create the new subscription
     * Also, at the click of the date widget a calendar will pop up
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub);
        nameField = (EditText) findViewById(R.id.editText);
        dateField = (EditText) findViewById(R.id.editText2);
        chargeField = (EditText) findViewById(R.id.editText3);
        commentField = (EditText) findViewById(R.id.editText4);

        dateField.setFocusable(false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calDate = Calendar.getInstance();
                new DatePickerDialog(EditSub.this, onDateSetListener,
                        calDate.get(Calendar.YEAR),
                        calDate.get(Calendar.MONTH),
                        calDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Intent intent = getIntent();
        String name = intent.getStringExtra("first");
        String date = intent.getStringExtra("second");
        String charge = intent.getStringExtra("third");
        String comment = intent.getStringExtra("fourth");
        position = intent.getIntExtra("fifth", -1);

        nameField.setText(name);
        dateField.setText(date);
        chargeField.setText(charge);
        commentField.setText(comment);


    }

    /**
     * https://stackoverflow.com/questions/15027454/how-to-get-onclick-in-datepickerdialog-ondatesetlistener
     *
     */
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calDate.set(Calendar.YEAR, year);
            calDate.set(Calendar.MONTH, month);
            calDate.set(Calendar.DAY_OF_MONTH, day);
            dateField.setText(year + "/" + (month + 1) + "/" + day);
        }
    };

    /**
     * Lauches the home screen page at the click of the Cancel button
     * @param view
     */

    public void doNothing(View view){
        Intent intent = new Intent(this, ViewSub.class);
        startActivity(intent);
    }

    /**
     * Launches the Subscription list page at the click of the Save button, passes the
     * info of the edited subscription and also checks for incorrect/null values
     * @param view
     */

    public void openViewAgain(View view){

        Intent intent = new Intent(this, ViewSub.class);
        String name = nameField.getText().toString();
        if (name.isEmpty() || name.length()>20) {
            nameField.setError("Enter name/Name too long");
            return;
        }
        String date = dateField.getText().toString();
        if (date.isEmpty()) {
            dateField.setError("Enter date");
            return;
        }
        String monthlyCharge = chargeField.getText().toString();
        if (monthlyCharge.isEmpty()) {
            chargeField.setError("Enter charge");
            return;
        }
        String comment = commentField.getText().toString();
        if (comment.length()>30) {
            commentField.setError("Comment too long");
            return;
        }

        intent.putExtra("first", name);
        intent.putExtra("second", date);
        intent.putExtra("third", monthlyCharge);
        intent.putExtra("fourth", comment);
        intent.putExtra("fifth", position);

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
