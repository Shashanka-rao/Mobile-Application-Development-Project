package com.example.miniprojectmad1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectmad1.MainClasses.ServiceCost;
import com.example.miniprojectmad1.Models.ServiceCostModel;
import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.R;

import java.util.ArrayList;

public class ServiceCostAdapter extends RecyclerView.Adapter<ServiceCostAdapter.MyViewHolder> {
    Context context;
    ArrayList<ServiceCostModel> list;

    public ServiceCostAdapter(Context context, ArrayList<ServiceCostModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.servicecost_item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServiceCostModel user = list.get(position);
        holder.DisplayKms.setText(user.getKmsIn()+"km");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView DisplayKms;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DisplayKms = itemView.findViewById(R.id.DisplayKms);
        }
    }

}
