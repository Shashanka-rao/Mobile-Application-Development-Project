package com.example.miniprojectmad1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniprojectmad1.MainClasses.UpdateSSManager;
import com.example.miniprojectmad1.Models.UserCreationModel;
import com.example.miniprojectmad1.Models.ViewBookingManagerModel;
import com.example.miniprojectmad1.R;

import java.util.ArrayList;

public class ViewBookingManagerAdapter extends RecyclerView.Adapter<ViewBookingManagerAdapter.MyViewHolder1> {
    Context context;
    ArrayList<ViewBookingManagerModel> list;


    public ViewBookingManagerAdapter(Context context, ArrayList<ViewBookingManagerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.viewbooking_item,parent,false);
       return new MyViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder1 holder, int position) {
        ViewBookingManagerModel user = list.get(position);

        holder.usNameBooked.setText("Username : "+user.getUserNameB());
        holder.tvFetchRegNo.setText(user.getvRegNoB());
        holder.tvServiceCenter.setText("Service Center : "+user.getLocation());
        holder.tvPickUp.setText("PickUp : "+user.getPickUpLoc());
        holder.tvDropOff.setText("DropOff : "+user.getDropLoc());
        holder.tvDateValue.setText(user.getSelectedDate());
        holder.tvTimeValue.setText(user.getSelectedTime());
        holder.trackSSBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateSSManager.class);
                intent.putExtra( "username",user.getUserNameB());
                intent.putExtra("vRegNo",user.getvRegNoB());
                intent.putExtra("time",user.getSelectedTime());
                intent.putExtra("date",user.getSelectedDate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder{

        TextView usNameBooked,tvFetchRegNo,tvServiceCenter,tvPickUp,tvDropOff,tvDateValue,tvTimeValue;
        Button trackSSBtn;
        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);

            usNameBooked = itemView.findViewById(R.id.usNameBooked);
            tvDropOff = itemView.findViewById(R.id.tvDropOff);
            tvFetchRegNo = itemView.findViewById(R.id.tvFetchRegNo);
            tvDateValue = itemView.findViewById(R.id.tvDateValue);
            tvServiceCenter = itemView.findViewById(R.id.tvServiceCenter);
            tvPickUp = itemView.findViewById(R.id.tvPickUp);
            tvTimeValue = itemView.findViewById(R.id.tvTimeValue);
            trackSSBtn = itemView.findViewById(R.id.trackSSBtn);


        }
    }


}



//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.viewbooking_item,parent,false);
//        return new MyViewHolder(v);    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        ViewBookingManagerModel user = list.get(position);
//        holder.tvFetchRegNo.setText(user.getvRegNo());
//        holder.tvServiceCenter.setText(user.getLocation());
//        holder.tvPickUp.setText(user.getPickUpLoc());
//        holder.tvDropOff.setText(user.getDropLoc());
//        holder.tvDateValue.setText(user.getSelectedDate());
//        holder.tvTimeVale.setText(user.getSelectedTime());
//
//
//    @Override
//    public int getItemCount() { return list.size();    }
//