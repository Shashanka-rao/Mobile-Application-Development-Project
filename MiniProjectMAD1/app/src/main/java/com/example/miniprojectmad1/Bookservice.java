package com.example.miniprojectmad1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.databinding.ActivityBookserviceBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class Bookservice extends AppCompatActivity {
    ActivityBookserviceBinding binding;

    private Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookserviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

     binding.backButtonBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Bookservice.this, Home.class);
                startActivity(intent);
            }
        });

     //copied

        binding.btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        binding.btnSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });

        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = binding.etLocation.getText().toString().trim();
                String selectedDate = binding.tvSelectedDate.getText().toString().trim();
                String selectedTime = binding.tvSelectedTime.getText().toString().trim();

                if (location.isEmpty() || selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(Bookservice.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
                } else {
                    // Process the booking (e.g., save to a database, send to server, etc.)
                    Toast.makeText(Bookservice.this, "Booking confirmed", Toast.LENGTH_SHORT).show();
                }
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

    private void showTimePicker() {
        int hour = selectedDate.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDate.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minutes) -> {
                    selectedDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    selectedDate.set(Calendar.MINUTE, minute);
                    updateSelectedTime();
                },
                hour,
                minute,
                false
        );

//        timePickerDialog.setCustomTitle(createTimePickerTitle());

        timePickerDialog.show();
    }

//    private View createTimePickerTitle() {
//        View titleView = getLayoutInflater().inflate(R.layout.custom_time_picker_title, null);
//        return titleView;
//    }

    private void updateSelectedDate() {
        binding.tvSelectedDate.setVisibility(View.VISIBLE);
        binding.tvSelectedDate.setText("Selected Date: " + dateFormatter.format(selectedDate.getTime()));
    }

    private void updateSelectedTime() {
        int hour = selectedDate.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDate.get(Calendar.MINUTE);

        selectedDate.set(Calendar.SECOND, 0);

        Calendar minTime = Calendar.getInstance();
        minTime.set(Calendar.HOUR_OF_DAY, 9);
        minTime.set(Calendar.MINUTE, 0);
        minTime.set(Calendar.SECOND, 0);

        Calendar maxTime = Calendar.getInstance();
        maxTime.set(Calendar.HOUR_OF_DAY, 18);
        maxTime.set(Calendar.MINUTE, 0);
        maxTime.set(Calendar.SECOND, 0);

        if (selectedDate.before(minTime) || selectedDate.after(maxTime)) {
            Toast.makeText(Bookservice.this, "Please select a time between 9:00 AM and 6:00 PM", Toast.LENGTH_SHORT).show();
        } else {
            binding.tvSelectedTime.setVisibility(View.VISIBLE);
            binding.tvSelectedTime.setText("Selected Time: " + timeFormatter.format(selectedDate.getTime()));
        }
    }
}
