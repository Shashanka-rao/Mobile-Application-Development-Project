package com.example.miniprojectmad1.AboutApp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.miniprojectmad1.MainClasses.Home;
import com.example.miniprojectmad1.MainClasses.HomeManager;
import com.example.miniprojectmad1.MainClasses.Login;
import com.example.miniprojectmad1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WizardActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ImageView dot1;
    private ImageView dot2;
    private ImageView dot3;
    private ImageView dot4;
    FirebaseAuth auth;

    private ViewPager2.OnPageChangeCallback pageChangeCallback = new ViewPager2.OnPageChangeCallback() {
        @Override
        public void onPageSelected(int position) {
            updateDots(position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        viewPager = findViewById(R.id.viewPager);
        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);
        dot4 = findViewById(R.id.dot4);

        WizardPagerAdapter adapter = new WizardPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(pageChangeCallback);


    }

    private void updateDots(int position) {
        dot1.setImageResource(position == 0 ? R.drawable.baseline_circle_24 : R.drawable.circle);
        dot2.setImageResource(position == 1 ? R.drawable.baseline_circle_24 : R.drawable.circle);
        dot3.setImageResource(position == 2 ? R.drawable.baseline_circle_24 : R.drawable.circle);
        dot4.setImageResource(position == 3 ? R.drawable.baseline_circle_24 : R.drawable.circle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager.unregisterOnPageChangeCallback(pageChangeCallback);
    }
}

class WizardPagerAdapter extends FragmentStateAdapter {
    public WizardPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new WizardFragment1();
            case 1:
                return new WizardFragment2();
            case 2:
                return new WizardFragment3();
            case 3:
                return new WizardFragment4();
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }
}