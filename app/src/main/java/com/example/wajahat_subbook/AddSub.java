package com.example.wajahat_subbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class AddSub extends Activity {

    private EditText nameField;
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

    }


    public void openViewAgain (View view3){
        Intent intent = new Intent(this, ViewSub.class);
        String name = nameField.getText().toString();
        String date = dateField.getText().toString();
        String monthlyCharge = chargeField.getText().toString();
        String comment = commentField.getText().toString();

        intent.putExtra("first", name);
        intent.putExtra("second", date);
        intent.putExtra("third", monthlyCharge);
        intent.putExtra("fourth", comment);
        startActivity(intent);
    }
}
