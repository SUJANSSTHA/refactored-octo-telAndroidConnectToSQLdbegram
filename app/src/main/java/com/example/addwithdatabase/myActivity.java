package com.example.addwithdatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class myActivity extends Activity {
    Button btnload;
    TextView txtResult;
    ArrayList<Person> list;
    MyDBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout;
        btnload = findViewById(R.id.btnload);
        txtResult = findViewById(R.id.txtResult);
        dbHelper = new MyDBHelper(this);
        Person p = new Person();
//        p.Name = "Ram";
//        p.Description = "Test dd";
        dbHelper.insertData(p);
        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });


    }
    public void loadData(){
    list = dbHelper.getPerson();
    txtResult.setText("");
        for (Person p:list) {
            txtResult.setText(txtResult.getText().toString()+"\n"+p.id);
        }
    }
}
