package com.Mahesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayData extends AppCompatActivity {
    TextView tvtotal;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    Adapter adapter;
    ArrayList<Model> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data);

        recyclerView = findViewById(R.id.recview);
        tvtotal = findViewById(R.id.tvtotal);
        setTitle("User list");
         dbHelper = new DBHelper(this);

        //for each loop
//        for (Model m :models){
//            System.out.println(m.getName());
//        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        models = dbHelper.getAllusers();
        tvtotal.setText("Total Users: " +models.size());

        adapter = new Adapter(models,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

    }
}