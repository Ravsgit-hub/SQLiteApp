package com.example.sqlite1app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Context context;
    EditText editname,editid,editphone,editrole,editcompany;
    Button btnsave,btnshow,btnupdate,btnedit;

    DbHandler db=new DbHandler(MainActivity.this);


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        editname=findViewById(R.id.editname);
        editphone=findViewById(R.id.editphone);
        editrole=findViewById(R.id.editrole);
        editid=findViewById(R.id.editid);
        editcompany=findViewById(R.id.editcompany);
        btnshow=findViewById(R.id.btnshow);
        btnsave=findViewById(R.id.btnsave);
        btnupdate=findViewById(R.id.btnupdate);
        btnedit=findViewById(R.id.btnedit);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#292325")));
        //taking data from onclick event of edit button for updation
        Intent i=getIntent();
        String Editevent=i.getStringExtra("UpdateEvent");
        if(Editevent!=null && Editevent.equals("true"))
        {
            editname.setFocusable(false);
            editphone.setFocusable(false);
            editrole.setFocusable(false);
            editid.setFocusable(false);
            editcompany.setFocusable(false);

        }
        else {
            editname.setEnabled(true);
            editphone.setEnabled(true);
            editrole.setEnabled(true);
            editid.setEnabled(true);
            editcompany.setEnabled(true);
        }
        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editname.setFocusableInTouchMode(true);
                editphone.setFocusableInTouchMode(true);
                editrole.setFocusableInTouchMode(true);
                editid.setFocusableInTouchMode(true);
                editcompany.setFocusableInTouchMode(true);

            }
        });
        String Updateevent=i.getStringExtra("UpdateEvent");
        if(Updateevent!=null && Updateevent.equals("true"))
        {
            btnupdate.setVisibility(View.VISIBLE);
            btnedit.setVisibility(View.VISIBLE);
            btnshow.setVisibility(View.INVISIBLE);
            btnsave.setVisibility(View.INVISIBLE);
            getSupportActionBar().setTitle("Edit Client");


        }
        else {
            btnupdate.setVisibility(View.INVISIBLE);
            btnedit.setVisibility(View.INVISIBLE);
            btnshow.setVisibility(View.VISIBLE);
            btnsave.setVisibility(View.VISIBLE);

        }

        String role =i.getStringExtra("role");
        String Name= i.getStringExtra("Name");
        String Phone= i.getStringExtra("Phone");
        String Company= i.getStringExtra("Company");
        int id= i.getIntExtra("ID",-1);
        editname.setText(Name);
        editphone.setText(Phone);
        editrole.setText(role);
        editid.setText(String.valueOf(id));
        editcompany.setText(Company);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=editname.getText().toString();
                String Phone=editphone.getText().toString();
                String Role=editrole.getText().toString();
                String Company=editcompany.getText().toString();
                String id=editid.getText().toString();
                db.updateClient(new model(Name,Phone,Role,Company),Integer.parseInt(id));
                startActivity(new Intent(MainActivity.this,DetailsAdapter.class));

            }
        });


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name=editname.getText().toString();
                String Phone=editphone.getText().toString();
                String Role=editrole.getText().toString();
                String Company=editcompany.getText().toString();
                db.addClient(new model(Name,Phone,Role,Company));
                Log.d("Reading: ", "Reading all contacts..");
                editname.setText("");
                editid.setText("");
                editphone.setText("");
                editcompany.setText("");
                editrole.setText("");


            }
        });

        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<model> clientlist = db.getAllClient();
                for (model cn : clientlist) {
                    String log = "Id: " + cn.getId() + " ,Name: " + cn.getName() + " ,Phone: " +
                            cn.getPhone()+",Role: "+cn.getRole()+",Company:"+cn.getCompany();
                    Log.d("Name: ", log);
                }
                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                intent.putExtra("list", (Serializable) clientlist);
                startActivity(intent);

//                db.deleteClient("Client", clientlist.get(intentExtra));
            }
        });
    }
}