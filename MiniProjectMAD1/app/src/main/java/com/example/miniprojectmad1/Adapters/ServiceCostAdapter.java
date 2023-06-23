package com.example.miniprojectmad1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectmad1.Models.ServiceCostModel;
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
        holder.BillMKmsU.setText(user.getKmsIn()+"km");
        holder.BillMDateU.setText(user.getDateIn());
        //desp
        holder.rsDespU.setText(user.getRsDesp());
        holder.npDespU.setText(user.getNpDesp());
        holder.ocDespU.setText(user.getOcDesp());
        holder.wDespU.setText(user.getwDesp());
        holder.lDespU.setText(user.getlDesp());
        //price
        holder.rsPriceU.setText(user.getRsPrice());
        holder.npPriceU.setText(user.getNpPrice());
        holder.ocPriceU.setText(user.getOcPrice());
        holder.wPriceU.setText(user.getwPrice());
        holder.lPriceU.setText(user.getlPrice());
        //total
        holder.etTotalU.setText(user.getBillIn());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView BillMKmsU,BillMDateU,etTotalU,rsDespU,npDespU,ocDespU,wDespU,lDespU,rsPriceU,npPriceU,ocPriceU,wPriceU,lPriceU;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            BillMKmsU = itemView.findViewById(R.id.BillMKmsU);
            BillMDateU = itemView.findViewById(R.id.BillMDateU);
            rsDespU = itemView.findViewById(R.id.rsDespU);
            npDespU = itemView.findViewById(R.id.npDespU);
            ocDespU = itemView.findViewById(R.id.ocDespU);
            wDespU = itemView.findViewById(R.id.wDespU);
            lDespU = itemView.findViewById(R.id.lDespU);
            rsPriceU = itemView.findViewById(R.id.rsPriceU);
            npPriceU = itemView.findViewById(R.id.npPriceU);
            ocPriceU = itemView.findViewById(R.id.ocPriceU);
            wPriceU = itemView.findViewById(R.id.wPriceU);
            lPriceU= itemView.findViewById(R.id.lPriceU);
            etTotalU= itemView.findViewById(R.id.etTotalU);

        }
    }

}