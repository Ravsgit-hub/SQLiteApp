package com.example.sqlite1app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<model> listclient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recyclerView=findViewById(R.id.recyclerview);
        listclient = (List<model>) getIntent().getSerializableExtra("list");
        DetailsAdapter detailsAdapter=new DetailsAdapter(this,listclient);
        recyclerView.setAdapter(detailsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#BA8C7E")));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
        return super.onOptionsItemSelected(item);
    }
}




