package com.example.wajahat_subbook;

import android.app.Activity;
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
import android.widget.Button;
import android.widget.EditText;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.zip.Inflater;

public class ViewSub extends AppCompatActivity {

    private static final String FILENAME = "sub_list.sav";
    private ArrayList<Subscription> subList;
    private ArrayAdapter<Subscription> adapter;
    private ListView subscriptionsList;
    private TextView totalCharge;
    private Float sum;

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
            Date date = null;
            Float monthlyCharge = Float.parseFloat(c);
            try {
                DateFormat df = new SimpleDateFormat("YYYY/MM/DD", Locale.CANADA);
                date = df.parse(d);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
            if (position == null || position == -1) {
                Subscription subs = new Subscription(name, date, monthlyCharge, comment);
                subList.add(subs);
                adapter.notifyDataSetChanged();
                saveInFile();
            } else {
                Subscription sub = subList.get(position);
                subList.remove(sub);
                Subscription subs = new Subscription(name, date, monthlyCharge, comment);
                subList.add(subs);
                adapter.notifyDataSetChanged();
                saveInFile();

            }
        }
    }

    public void goHome(View view){
        Intent intent = new Intent(this, SubBook.class);
        startActivity(intent);
    }

    public void calculateTc(TextView totalCharge, Float sum){
        for (int i = 0; i<subList.size(); i++){
            Subscription sub = subList.get(i);
            sum += sub.getSubCharge();
        }
        String totalSum = sum.toString();
        totalCharge.setText(totalSum);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.popup, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()){
            case R.id.option1:
                subList.remove(info.position);
                adapter.notifyDataSetChanged();
                saveInFile();
                return true;
            //latest changes from here onwards
            case R.id.option2:
                Intent intent = new Intent(this, EditSub.class);
                Subscription sub1 = subList.get(info.position);
                String name = sub1.getSubName();
                Date date = sub1.getDate();
                Float charge = sub1.getSubCharge();
                String comment = sub1.getComment();

                String d = date.toString();
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


    @Override
    protected void onStart() {
        super.onStart();
    }

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
