package com.sithum.mdcw.activities.medicaltypeactivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.sithum.R;
import com.sithum.mdcw.fragments.BloodGlucoseNewFragment;
import com.sithum.mdcw.fragments.BloodGlucosePreFragment;
import com.sithum.mdcw.fragments.VaccineNewFragment;
import com.sithum.mdcw.fragments.VaccinePreFragment;

public class BloodGlucoseActivity extends AppCompatActivity {

    Button btnPrevious, btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_blood_glucose);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnPrevious = findViewById(R.id.btn_previous);
        btnNew = findViewById(R.id.btn_new);

        // Load default fragment
        loadFragment(new BloodGlucoseNewFragment());

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BloodGlucosePreFragment());
            }
        });

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new BloodGlucoseNewFragment());
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}