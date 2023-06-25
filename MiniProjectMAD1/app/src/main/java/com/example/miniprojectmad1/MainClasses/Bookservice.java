package com.example.miniprojectmad1.MainClasses;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.miniprojectmad1.Models.BookServiceModel;
import com.example.miniprojectmad1.Models.UserCreationModel;
import com.example.miniprojectmad1.R;
import com.example.miniprojectmad1.databinding.ActivityBookserviceBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;



public class Bookservice extends AppCompatActivity {
    private ActivityBookserviceBinding binding;

    private Calendar selectedDate = Calendar.getInstance();
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());

    private CheckBox checkBoxPD;
    ArrayList<UserCreationModel> list;
    EditText etDrop1;
    FirebaseAuth auth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    public String vehicleRegU,currentUid,userNameU;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookserviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        etDrop1 = findViewById(R.id.etDrop1);
        list = new ArrayList<>();


        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
           currentUid = auth.getCurrentUser().getUid();
        }

        DatabaseReference reference = database.getReference().child("Users").child("UserData");
        //fetch email
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserCreationModel user = dataSnapshot.getValue(UserCreationModel.class);
                    if(currentUid.equals(dataSnapshot.child("userId").getValue()))
                    {
                        vehicleRegU = user.getvRegNo();
                        userNameU = user.getUsername();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
        binding.checkBoxPD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
                String pDropLoc = binding.etDrop1.getText().toString().trim();
                //modified - test
//                if(checkBoxPD.isChecked()) {
//                    pUpLoc = binding.etPickUp.getText().toString().trim();
//                    pDropLoc = binding.etDrop1.getText().toString().trim();
//                }
//                else
//                {
//                    pUpLoc="Not Opted";
//                    pDropLoc="Not Opted";
//                }

                model.setLocation(location);
                model.setSelectedDate(selectedDate);
                model.setSelectedTime(selectedTime);
                model.setPickUpLoc(pUpLoc);
                model.setDropLoc(pDropLoc);
                model.setUserIdBook(currentUid);
                model.setUserNameB(userNameU);
                model.setvRegNoB(vehicleRegU);

                if (location.isEmpty() || selectedDate.isEmpty() || selectedTime.isEmpty()) {
                    Toast.makeText(Bookservice.this, "Please fill in all the details", Toast.LENGTH_SHORT).show();
                }
                else {
                FirebaseDatabase.getInstance().getReference("Users").child("Booking").push().setValue(model);
                Toast.makeText(Bookservice.this, "Booking confirmed", Toast.LENGTH_SHORT).show();

                    //to clear after submission
                    //checkbox
                    if(checkBoxPD.isChecked()){
                        binding.checkBoxPD.performClick();}
                    // time
                    binding.SelectedTimeDisplay.setVisibility(View.INVISIBLE);
                    binding.tvSelectedTime.setVisibility(View.INVISIBLE);
                    //date
                    binding.SelectedDateDisplay.setVisibility(View.INVISIBLE);
                    binding.tvSelectedDate.setVisibility(View.INVISIBLE);
                    //Location
                    binding.etLocation.setText("");
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
