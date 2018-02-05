package com.example.wajahat_subbook;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class AddSub extends AppCompatActivity {

    private EditText nameField;
    private Calendar calDate;
    private EditText dateField;
    private EditText chargeField;
    private EditText commentField;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);
        nameField = (EditText) findViewById(R.id.editText);
        dateField = (EditText) findViewById(R.id.editText2);
        chargeField = (EditText) findViewById(R.id.editText3);
        commentField = (EditText) findViewById(R.id.editText4);

        dateField.setFocusable(false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");

        dateField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calDate = Calendar.getInstance();
                new DatePickerDialog(AddSub.this, onDateSetListener,
                        calDate.get(Calendar.YEAR),
                        calDate.get(Calendar.MONTH),
                        calDate.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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

    public void doNothing(View view){
        Intent intent = new Intent(this, SubBook.class);
        startActivity(intent);
    }

    public void openViewAgain (View view3){
        Intent intent = new Intent(this, ViewSub.class);
        String name = nameField.getText().toString();
        String date = dateField.getText().toString();
        String monthlyCharge = chargeField.getText().toString();
        String comment = commentField.getText().toString();
        Integer position = null;

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
