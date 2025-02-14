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
import com.sithum.mdcw.activities.medicaltypeactivities.AllergyActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.AppointmentActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.BloodGlucoseActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.BloodPressureActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.ExaminationActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.InjuriesActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.LabTestActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.MedicalVisitActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.NotesActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.OverviewActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.OxygenActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.PrescriptionActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.SurgeryActivity;
import com.sithum.mdcw.activities.medicaltypeactivities.VaccineActivity;

public class LogListActivity extends AppCompatActivity {

    String name;
    TextView txtname;
    ImageButton btnback, btnmore;
    LinearLayout vaccine, injuries, overview, medicalvisit, allergy, bloodpressure, bloodglucose, oxygen, examination, prescription, labtest, Surgery, notes, appoinment;

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
        injuries = findViewById(R.id.injuries);
        overview = findViewById(R.id.overview);
        medicalvisit = findViewById(R.id.medicalvisit);
        allergy = findViewById(R.id.allergy);
        bloodpressure = findViewById(R.id.bloodpressure);
        bloodglucose = findViewById(R.id.bloodglucose);
        oxygen = findViewById(R.id.oxygen);
        examination = findViewById(R.id.examination);
        prescription = findViewById(R.id.prescription);
        labtest = findViewById(R.id.labtest);
        Surgery = findViewById(R.id.Surgery);
        notes = findViewById(R.id.notes);
        appoinment = findViewById(R.id.appoinment);

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

        injuries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, InjuriesActivity.class);
                startActivity(intent);
            }
        });

        overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, OverviewActivity.class);
                startActivity(intent);
            }
        });

        medicalvisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, MedicalVisitActivity.class);
                startActivity(intent);
            }
        });

        allergy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, AllergyActivity.class);
                startActivity(intent);
            }
        });

        bloodpressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, BloodPressureActivity.class);
                startActivity(intent);
            }
        });

        bloodglucose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, BloodGlucoseActivity.class);
                startActivity(intent);
            }
        });

        oxygen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, OxygenActivity.class);
                startActivity(intent);
            }
        });

        examination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, ExaminationActivity.class);
                startActivity(intent);
            }
        });

        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, PrescriptionActivity.class);
                startActivity(intent);
            }
        });

        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, LabTestActivity.class);
                startActivity(intent);
            }
        });

        Surgery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, SurgeryActivity.class);
                startActivity(intent);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, NotesActivity.class);
                startActivity(intent);
            }
        });

        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogListActivity.this, AppointmentActivity.class);
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