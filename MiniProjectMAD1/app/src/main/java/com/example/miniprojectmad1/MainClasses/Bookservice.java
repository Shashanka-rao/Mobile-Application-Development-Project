package com.example.miniprojectmad1.MainClasses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojectmad1.Models.BookServiceModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityBookserviceBinding;
import com.example.miniprojectmad1.trackService;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class Bookservice extends AppCompatActivity {
    private ActivityBookserviceBinding binding;

    private Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private CheckBox checkBox;
    EditText etDrop1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookserviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        etDrop1 = findViewById(R.id.etDrop1);

//   Back button
     binding.backButtonBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

        //checkbox
        binding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    binding.tvDispDetails.setVisibility(View.VISIBLE);
                    binding.pickUpTV.setVisibility(View.VISIBLE);
                    binding.DropTV.setVisibility(View.VISIBLE);
                    binding.driverTV.setVisibility(View.VISIBLE);
                    binding.etPickUp.setVisibility(View.VISIBLE);
                    binding.etDrop1.setVisibility(View.VISIBLE);
                }
                else
                {
                    binding.driverTV.setVisibility(View.INVISIBLE);
                    binding.tvDispDetails.setVisibility(View.INVISIBLE);
                    binding.pickUpTV.setVisibility(View.INVISIBLE);
                    binding.DropTV.setVisibility(View.INVISIBLE);
                    binding.driverTV.setVisibility(View.INVISIBLE);
                    binding.etPickUp.setVisibility(View.INVISIBLE);
                    binding.etDrop1.setVisibility(View.INVISIBLE);


                    binding.etPickUp.setText("");
                    binding.etDrop1.setText("");
                }
            }
        });

        binding.btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookServiceModel model = new BookServiceModel();

                String location = binding.etLocation.getText().toString().trim();
                String selectedDate = binding.tvSelectedDate.getText().toString().trim();
                String selectedTime = binding.tvSelectedTime.getText().toString().trim();
                String pUpLoc = binding.etPickUp.getText().toString().trim();
//                String pDropLoc = binding.etDrop1.getText().toString().trim();

                model.setLocation(location);
                model.setSelectedDate(selectedDate);
                model.setSelectedTime(selectedTime);
                model.setPickUpLoc(pUpLoc);
//                model.setDropLoc(pDropLoc);
                model.setDropLoc(binding.etDrop1.getText().toString());

                if (location.isEmpty() || selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(Bookservice.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
                } else {
                    // Process the booking (e.g., save to a database, send to server, etc.)
                    FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(model);
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

        binding.SelectedDateDisplay.setVisibility(View.VISIBLE);
        binding.tvSelectedDate.setVisibility(View.VISIBLE);
        binding.tvSelectedDate.setText(dateFormatter.format(selectedDate.getTime()));
    }

    private void updateSelectedTime() {
        int hour = selectedDate.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDate.get(Calendar.MINUTE);

        selectedDate.set(Calendar.SECOND, 0);

//        Calendar minTime = Calendar.getInstance();
//        minTime.set(Calendar.HOUR_OF_DAY, 9);
//        minTime.set(Calendar.MINUTE, 0);
//        minTime.set(Calendar.SECOND, 0);
//
//        Calendar maxTime = Calendar.getInstance();
//        maxTime.set(Calendar.HOUR_OF_DAY, 18);
//        maxTime.set(Calendar.MINUTE, 0);
//        maxTime.set(Calendar.SECOND, 0);
//
//        if (selectedDate.before(minTime) || selectedDate.after(maxTime)) {
//            Toast.makeText(Bookservice.this, "Please select a time between 9:00 AM and 6:00 PM", Toast.LENGTH_SHORT).show();
//        } else {
        binding.SelectedTimeDisplay.setVisibility(View.VISIBLE);
        binding.tvSelectedTime.setVisibility(View.VISIBLE);
            binding.tvSelectedTime.setText(timeFormatter.format(selectedDate.getTime()));
//        }
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
