package com.example.wajahat_subbook;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AddSub extends Activity {

    private static final String FILENAME = "sub_list.sav";
    private EditText nameField;
    private EditText dateField;
    private EditText chargeField;
    private EditText commentField;
    private ListView subscriptionsList;

    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub);

        nameField = (EditText) findViewById(R.id.editText);
        dateField = (EditText) findViewById(R.id.editText2);
        chargeField = (EditText) findViewById(R.id.editText3);
        commentField = (EditText) findViewById(R.id.editText4);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        subscriptionsList = (ListView) findViewById(R.id.subscriptionsList);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                String name = nameField.getText().toString();
                Date date = null;
                try {
                    DateFormat df = new SimpleDateFormat("YYYY/MM/DD", Locale.CANADA);
                    date = df.parse(dateField.getText().toString());
                } catch (ParseException pe) {
                    pe.printStackTrace();
                }
                Float monthlyCharge = Float.parseFloat(chargeField.getText().toString());
                String comment = commentField.getText().toString();

                Subscription subs = new GeneralSubscription(name, date, monthlyCharge, comment);
                subList.add(subs);
                adapter.notifyDataSetChanged();
                saveInFile();
            }
        });

    }

        @Override
        protected void onStart() {
            super.onStart();
            loadFromFile();
            adapter = new ArrayAdapter<Subscription>(this, R.layout.activity_view_sub, subList);
            subscriptionsList.setAdapter(adapter);
        }

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2018-01-28
            Type listType = new TypeToken<ArrayList<GeneralSubscription>>(){}.getType();
            subList = gson.fromJson(in, listType);


        } catch (FileNotFoundException e) {
            subList = new ArrayList<Subscription>();

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    private void saveInFile() {

        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(subList, out);
            out.flush();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}




