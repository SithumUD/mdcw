package com.sithum.mdcw.activities;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sithum.R;
import com.sithum.mdcw.database.DatabaseHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPersonActivity extends AppCompatActivity {

    Button btnsave, btnselectbirthday;
    ImageButton btnback;
    Spinner spbloodtype;
    RadioGroup radiogroupgender;
    RadioButton rbmale, rbfemale;
    ImageView profileimage;
    EditText txtname, txtnumber, txtemail, txtnotes, txtage;

    String[] bloodtypes = {"A+", "A-", "B+", "B-", "o+", "o-", "AB+", "AB-"};
    String selectedGender, selectedBloodType;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri;

    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_person);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);

        btnsave = findViewById(R.id.btnsave);
        btnback = findViewById(R.id.btnback);
        spbloodtype = findViewById(R.id.spbloodtype);
        btnselectbirthday = findViewById(R.id.btnselectbirthday);
        radiogroupgender = findViewById(R.id.radioGroupGender);
        rbmale = findViewById(R.id.rbmale);
        rbfemale = findViewById(R.id.rbfemale);
        profileimage = findViewById(R.id.profileimage);
        txtname = findViewById(R.id.txtname);
        txtemail = findViewById(R.id.txtemail);
        txtnumber = findViewById(R.id.txtnumber);
        txtnotes = findViewById(R.id.txtnotes);
        txtage = findViewById(R.id.txtage);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bloodtypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spbloodtype.setAdapter(adapter);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtname.getText().toString();
                String birthday = btnselectbirthday.getText().toString();
                String age = txtage.getText().toString();
                selectedGender = (radiogroupgender.getCheckedRadioButtonId() == R.id.rbmale) ? "Male" : "Female";
                selectedBloodType = spbloodtype.getSelectedItem().toString();

                if (name.isEmpty() || birthday.isEmpty() || age.isEmpty()) {
                    Toast.makeText(AddPersonActivity.this, "Name, Birthday, and Age are required!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert profile image to BLOB
                byte[] imageBytes = null;
                if (selectedImageUri != null) {
                    imageBytes = getImageBytes(selectedImageUri);
                }

                // Save data into the database
                boolean isInserted = dbHelper.insertPerson(name, birthday, Integer.parseInt(age), selectedGender, selectedBloodType, imageBytes);

                if (isInserted) {
                    // Navigate to MainActivity if successful
                    Intent intent = new Intent(AddPersonActivity.this, LogListActivity.class);
                    intent.putExtra("person_name", name);
                    startActivity(intent);
                    finish();
                } else {
                    // Show error message if not successful
                    Toast.makeText(AddPersonActivity.this, "Failed to save details. Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spbloodtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedBloodType = bloodtypes[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnselectbirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                // Set today's date in the calendar
                int currentYear = calendar.get(Calendar.YEAR);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddPersonActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, month, dayOfMonth);

                                if (selectedDate.after(calendar)) {
                                    Toast.makeText(AddPersonActivity.this, "Please select a valid date (not in the future).", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                String formattedDate = dateFormat.format(selectedDate.getTime());
                                btnselectbirthday.setText(formattedDate);

                                int age = calculateAge(selectedDate);

                                txtage.setText(String.valueOf(age));
                            }
                        },
                        currentYear,
                        currentMonth,
                        currentDay
                );

                // Restrict the date picker to not allow future dates
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

                datePickerDialog.show();
            }
        });

        radiogroupgender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rbmale) {
                    selectedGender = rbmale.getText().toString();
                } else if (checkedId == R.id.rbfemale) {
                    selectedGender = rbfemale.getText().toString();
                }
            }
        });

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");

                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            // Get the selected image URI
            selectedImageUri = data.getData();

            // Display the selected image in the ImageView
            profileimage.setImageURI(selectedImageUri);
        } else {
            Toast.makeText(this, "Image selection failed!", Toast.LENGTH_SHORT).show();
        }
    }

    private int calculateAge(Calendar birthDate) {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

        // If the birthday hasn't occurred yet this year, subtract 1 from age
        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DAY_OF_MONTH) < birthDate.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }

        return age;
    }

    private void clearFields() {
        txtname.setText("");
        btnselectbirthday.setText("Select Birthday");
        txtage.setText("");
        radiogroupgender.clearCheck();
        spbloodtype.setSelection(0);
    }

    private byte[] getImageBytes(Uri imageUri) {
        try {
            ContentResolver contentResolver = getContentResolver();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}