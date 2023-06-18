package com.example.miniprojectmad1.AboutApp;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.miniprojectmad1.MainClasses.Login;
import com.example.miniprojectmad1.R;

public class WizardFragment2 extends Fragment {
    private Button btnNext,btnSkip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wizard2, container, false);
        btnNext = view.findViewById(R.id.btnNext2);
        btnSkip = view.findViewById(R.id.btnSkip2);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager2 viewPager = requireActivity().findViewById(R.id.viewPager);
                viewPager.setCurrentItem(2);
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        });

        return view;
    }
}