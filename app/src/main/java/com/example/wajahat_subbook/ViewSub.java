/* Copyright (c) 2018 Sameerah Wajahat, CMPUT 301, University of Alberta - All Rights Reserved.
* You may use, distribute or modify this code under terms and conditions of Code of Student Behaviour at
* University of Alberta.
* You can find a copy of the license in this project. Otherwise please contact wajahat@ualberta.ca
*/

package com.example.wajahat_subbook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Sameerah Wajahat
 * This activity allows the user to view the list of subscriptions and total charge of
 * all the subscriptions, it also receives the edited or new subscription for other activities
 * @see AddSub
 * @see EditSub
 * @see Subscription
 */

public class ViewSub extends AppCompatActivity {

    private static final String FILENAME = "sub_list.sav";      //File that saves the list of subscriptions
    private ArrayList<Subscription> subList;        //Arraylist for the subscriptions
    private ArrayAdapter<Subscription> adapter;     //ArrayAdapter for the subscriptions
    private ListView subscriptionsList;     //Widget that displays the list
    private TextView totalCharge;       //Widget that displays the total charge for all subscriptions
    private Float sum;      //total charge of the subscriptions

    /**
     * Displays the page that views the subscription list and add/edits the item in the list
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sub);
        subscriptionsList = (ListView) findViewById(R.id.subscriptionsList);
        totalCharge = (TextView) findViewById(R.id.tcNumeric);
        registerForContextMenu(subscriptionsList);


        loadFromFile();
        adapter = new ArrayAdapter<Subscription>(this, R.layout.list_item, subList);
        subscriptionsList.setAdapter(adapter);

        Intent intent = getIntent();
        Integer position = intent.getIntExtra("fifth", -1);
        String name = intent.getStringExtra("first");
        if (name != null) {
            String d = intent.getStringExtra("second");
            String c = intent.getStringExtra("third");
            String comment = intent.getStringExtra("fourth");
            Calendar date = null;
            Float monthlyCharge = Float.parseFloat(c);
            try {
                date = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                date.setTime(df.parse(d));
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            if (position == null || position == -1) {
                Subscription subs = new Subscription(name, date, monthlyCharge, comment);
                subList.add(subs);
                adapter.notifyDataSetChanged();
                saveInFile();
            } else {
                Subscription subs = new Subscription(name, date, monthlyCharge, comment);
                subList.set(position, subs);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }
    }

    /**
     * Launches the home page activity on the click of the Home button
     * @param view
     */

    public void goHome(View view){
        Intent intent = new Intent(this, SubBook.class);
        startActivity(intent);
    }

    /**
     * Creates a popup menu upon the Long press of an item on the list
     * @param menu
     * @param view
     * @param menuInfo
     */

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
    }

    /**
     * The popup menu contains two options of edit and delete - upon the click of edit (option 2)
     * passes intent and launches an activity; upon the click of delete (option 1),
     * removes the item from the list
     * @param item
     * @return boolean value
     */

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.option1:
                subList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile();
                sum = new Float(0);
                for (int i = 0; i<subList.size(); i++){
                    Subscription sub = subList.get(i);
                    sum += sub.getSubCharge();
                }
                String totalSum = String.format("$%,.2f", sum);
                totalCharge.setText(totalSum);
                return true;

            case R.id.option2:
                Intent intent = new Intent(this, EditSub.class);
                Subscription sub1 = subList.get(info.position);
                String name = sub1.getSubName();
                Calendar date = sub1.getDate();
                Float charge = sub1.getSubCharge();
                String comment = sub1.getComment();

                String d = date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.DAY_OF_MONTH);
                String c = charge.toString();

                Integer position = info.position;

                intent.putExtra("first", name);
                intent.putExtra("second", d);
                intent.putExtra("third", c);
                intent.putExtra("fourth", comment);
                intent.putExtra("fifth", position);

                startActivity(intent);
                return true;
            // to here
        }

        return super.onContextItemSelected(item);
    }

    /**
     * Calculates the total charge of all the subscriptions present in the list
     */

    @Override
    protected void onStart() {
        super.onStart();
        //https://stackoverflow.com
        sum = new Float(0);
        for (int i = 0; i<subList.size(); i++){
            Subscription sub = subList.get(i);
            sum += sub.getSubCharge();
        }
        String totalSum = String.format("$%,.2f", sum);
        totalCharge.setText(totalSum);
    }

    /**
     * Taken from lonelytwitter - CMPUT 301 Lab - loads the subscriptions from the file
     */

    private void loadFromFile() {

        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2018-01-28
            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();
            subList = gson.fromJson(in, listType);
            if (subList == null){
                subList = new ArrayList<>();
            }


        } catch (FileNotFoundException e) {
            subList = new ArrayList<Subscription>();

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    /**
     * Taken from lonelytwitter - CMPUT 301 Lab - saves the subscriptions in the file
     */

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
