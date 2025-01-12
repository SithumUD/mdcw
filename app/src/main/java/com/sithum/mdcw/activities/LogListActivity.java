package com.sithum.mdcw.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sithum.R;
import com.sithum.mdcw.activities.medicaltypeactivities.VaccineActivity;

public class LogListActivity extends AppCompatActivity {

    String name;
    TextView txtname;
    ImageButton btnback, btnmore;
    LinearLayout vaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        name = intent.getStringExtra("person_name");

        txtname = findViewById(R.id.txtname);
        btnback = findViewById(R.id.btnback);
        btnmore = findViewById(R.id.btnmore);
        vaccine = findViewById(R.id.vaccine);

        txtname.setText(name);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        vaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, VaccineActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showPopup(View anchorView) {
        // Inflate the popup layout
        View popupView = LayoutInflater.from(this).inflate(R.layout.popup_more, null);

        // Create the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT, true);

        // Find buttons in the popup layout
        Button btnOption1 = popupView.findViewById(R.id.btn_option1);
        Button btnOption2 = popupView.findViewById(R.id.btn_option2);
        Button btnOption3 = popupView.findViewById(R.id.btn_option3);

        // Set click listeners for each button
        btnOption1.setOnClickListener(v -> {
            Toast.makeText(this, "Option 1 clicked", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        btnOption2.setOnClickListener(v -> {
            Toast.makeText(this, "Option 2 clicked", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        btnOption3.setOnClickListener(v -> {
            Toast.makeText(this, "Option 3 clicked", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });

        // Show the popup window anchored to the "More" button
        popupWindow.showAsDropDown(anchorView, 0, 10);
    }
}