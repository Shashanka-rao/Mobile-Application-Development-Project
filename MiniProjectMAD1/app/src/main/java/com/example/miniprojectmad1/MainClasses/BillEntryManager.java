package com.example.miniprojectmad1.MainClasses;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.Models.ServiceHistoryModel;
import com.example.miniprojectmad1.databinding.ActivityBillEntryManagerBinding;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BillEntryManager extends AppCompatActivity {
    ActivityBillEntryManagerBinding binding;


    private Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    float frsPrice,fnpPrice,focPrice,fwPrice,flPrice,fTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBillEntryManagerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //fetch from USS
        Intent intent= getIntent();
        String unameBill = intent.getExtras().getString("uname");
        String VRegNoBill = intent.getExtras().getString("regNo");
        String timeBill = intent.getExtras().getString("time");
        String dateBill = intent.getExtras().getString("date");

// Auto-Fill
        binding.BillMDate.setText(dateBill);
        binding.BillMUsername.setText(unameBill);
        binding.BillMVRegNo.setText(VRegNoBill);

        binding.calculateTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calculate Total
                String srsPrice = String.valueOf(binding.rsPrice.getText());
                String snpPrice = String.valueOf(binding.npPrice.getText());
                String socPrice = String.valueOf(binding.ocPrice.getText());
                String swPrice = String.valueOf(binding.wPrice.getText());
                String slPrice = String.valueOf(binding.lPrice.getText());

                //convert to float

                try {
                    frsPrice = Float.parseFloat(srsPrice);
                    fnpPrice = Float.parseFloat(snpPrice);
                    focPrice = Float.parseFloat(socPrice);
                    fwPrice = Float.parseFloat(swPrice);
                    flPrice = Float.parseFloat(slPrice);
                    // Use the float value
                } catch (NumberFormatException e) {
                    // Handle the exception (e.g., display an error message)
                    e.printStackTrace();
                }


                //equation
                fTotal = frsPrice+fnpPrice+focPrice+fwPrice+flPrice;
                //set total
                binding.etTotal.setText( String.valueOf(fTotal));
            }
        });


        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceHistoryModel model = new ServiceHistoryModel();
                model.setUsername(binding.BillMUsername.getText().toString());
                model.setvRegNo(binding.BillMVRegNo.getText().toString());
                model.setKmsIn(binding.BillMKms.getText().toString());
                model.setBillIn(binding.etTotal.getText().toString());
                model.setDateIn(binding.BillMDate.getText().toString());
                //Description passed
                model.setRsDesp(binding.rsDesp.getText().toString());
                model.setNpDesp(binding.npDesp.getText().toString());
                model.setOcDesp(binding.ocDesp.getText().toString());
                model.setwDesp(binding.wDesp.getText().toString());
                model.setlDesp(binding.lDesp.getText().toString());
                //Price passed
                model.setRsPrice(binding.rsPrice.getText().toString());
                model.setNpPrice(binding.npPrice.getText().toString());
                model.setOcPrice(binding.ocPrice.getText().toString());
                model.setwPrice(binding.wPrice.getText().toString());
                model.setlPrice(binding.lPrice.getText().toString());






                FirebaseDatabase.getInstance().getReference().child("Services").child("Bills").push().setValue(model);
                Toast.makeText(com.example.miniprojectmad1.MainClasses.BillEntryManager.this, "Saved Succesfully", Toast.LENGTH_SHORT).show();


                binding.BillMDate.setText("");
                binding.etTotal.setText("");
                binding.BillMKms.setText("");
                onBackPressed();
            }
        });

        //date selector

        binding.BillMDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });



    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    selectedDate.set(Calendar.YEAR, year);
                    selectedDate.set(Calendar.MONTH, monthOfYear);
                    selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateSelectedDate();
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    private void updateSelectedDate() {
        binding.BillMDate.setText(dateFormatter.format(selectedDate.getTime()));
    }
}