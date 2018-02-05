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

public class EditSub extends AppCompatActivity {

    private EditText nameField;
    private EditText dateField;
    private Calendar calDate;
    private EditText chargeField;
    private EditText commentField;
    private Integer position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub);
        nameField = (EditText) findViewById(R.id.editText);
        dateField = (EditText) findViewById(R.id.editText2);
        chargeField = (EditText) findViewById(R.id.editText3);
        commentField = (EditText) findViewById(R.id.editText4);

        dateField.setFocusable(false);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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
        position = intent.getIntExtra("fifth", 0);

        nameField.setText(name);
        dateField.setText(df.format(date));
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

    public void doNothing(View view){
        Intent intent = new Intent(this, ViewSub.class);
        startActivity(intent);
    }

    public void openViewAgain(View view){

        Intent intent = new Intent(this, ViewSub.class);
        String name = nameField.getText().toString();
        String date = dateField.getText().toString();
        String monthlyCharge = chargeField.getText().toString();
        String comment = commentField.getText().toString();

        intent.putExtra("first", name);
        intent.putExtra("second", date);
        intent.putExtra("third", monthlyCharge);
        intent.putExtra("fourth", comment);
        intent.putExtra("fifth", position);

        startActivity(intent);
    }
}
