package com.example.wajahat_subbook;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditSub extends Activity {

    private EditText nameField;
    private EditText dateField;
    private EditText chargeField;
    private EditText commentField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sub);
        nameField = (EditText) findViewById(R.id.editText);
        dateField = (EditText) findViewById(R.id.editText2);
        chargeField = (EditText) findViewById(R.id.editText3);
        commentField = (EditText) findViewById(R.id.editText4);

        Intent intent = getIntent();
        String name = intent.getStringExtra("first");
        String date = intent.getStringExtra("second");
        String charge = intent.getStringExtra("third");
        String comment = intent.getStringExtra("fourth");

        nameField.setText(name);
        dateField.setText(date);
        chargeField.setText(charge);
        commentField.setText(comment);


    }

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
        startActivity(intent);
    }
}
