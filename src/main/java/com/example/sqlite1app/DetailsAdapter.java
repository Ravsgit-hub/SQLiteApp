package com.example.sqlite1app;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DBviewHolder>{
    Context context;
    List<model> listclient;
    DbHandler db;


    public DetailsAdapter(Context context, List<model> listclient) {
        this.context = context;
        this.listclient = listclient;

    }

    @NonNull
    @Override
    public DBviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.details, null);
        RecyclerView.LayoutParams lp;
        lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);

        return new DBviewHolder(itemLayoutView);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull DBviewHolder holder,int position) {
        model model=listclient.get(position);
        holder.textname.setText(model.getName());
        holder.textid.setText(String.valueOf(model.getId()));
        holder.textphone.setText(model.getPhone());
        holder.textrole.setText(model.getRole());
        holder.textcompany.setText(model.getCompany());


        holder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db=new DbHandler(context);
                int i=model.getId();
                db.deleteClient(i);
            }
        });

        holder.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db=new DbHandler(context);
                Toast.makeText(context, ""+model.getId(), Toast.LENGTH_SHORT).show();
                String role = model.getRole();
                String Name= model.getName();
                String Phone= model.getPhone();
                String Company= model.getCompany();
                int id=model.getId();

             Intent i=new Intent(context, MainActivity.class);
                i.putExtra("UpdateEvent","true");
                i.putExtra("EditEvent","true");
                i.putExtra("role",role);
                i.putExtra("Name",Name);
                i.putExtra("Phone",Phone);
                i.putExtra("Company",Company);
                i.putExtra("id",id);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listclient.size();
    }

    public class DBviewHolder extends RecyclerView.ViewHolder{
        TextView textname,textid,textphone,textrole,textcompany;
        Button btndelete,btnedit;
        public DBviewHolder(@NonNull View itemView) {
            super(itemView);
            textname=itemView.findViewById(R.id.textname);
            textid=itemView.findViewById(R.id.textid);
            textphone=itemView.findViewById(R.id.textphone);
            textrole=itemView.findViewById(R.id.textrole);
            textcompany=itemView.findViewById(R.id.textcompany);
            btndelete=itemView.findViewById(R.id.btndelete);
            btnedit=itemView.findViewById(R.id.btnedit);
        }

    }
}



