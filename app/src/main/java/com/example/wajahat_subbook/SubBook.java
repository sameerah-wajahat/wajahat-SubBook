package com.example.wajahat_subbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SubBook extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book);
    }

    public void openAddScreen(View view1){
        Intent intent1 = new Intent(this, AddSub.class);
        startActivity(intent1);
    }

    public void openViewScreen(View view2){
        Intent intent2 = new Intent(this, ViewSub.class);
        startActivity(intent2);
    }
}
