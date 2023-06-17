package com.example.miniprojectmad1.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;

import java.util.ArrayList;


public class ServiceHistoryAdapter extends RecyclerView.Adapter<ServiceHistoryAdapter.MyViewHolder> {

    Context context;
    ArrayList<ServiceHistoryModel> list;

    public ServiceHistoryAdapter(Context context, ArrayList<ServiceHistoryModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.servicehistory_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServiceHistoryModel user = list.get(position);
        holder.serviceNumber.setText(""+(position+1));
        holder.kmsInTV.setText(user.getKmsIn());
        holder.dateInTV.setText(user.getDateIn());
        holder.billInTV.setText(user.getBillIn());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView kmsInTV,dateInTV,billInTV,serviceNumber;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            serviceNumber = itemView.findViewById(R.id.serviceNumber);
            kmsInTV = itemView.findViewById(R.id.kmsInTV);
            dateInTV = itemView.findViewById(R.id.dateInTV);
            billInTV = itemView.findViewById(R.id.billInTV);


        }
    }

}