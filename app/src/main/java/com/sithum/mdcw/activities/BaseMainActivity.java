package com.sithum.mdcw.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sithum.R;
import com.sithum.mdcw.adapter.PersonAdapter;
import com.sithum.mdcw.database.DatabaseHelper;
import com.sithum.mdcw.model.Person;

import java.util.ArrayList;
import java.util.List;

public class BaseMainActivity extends AppCompatActivity {

    ImageButton btnaddperson, btnsearch;
    Button flotbtnaddperson;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private List<Person> personList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_base_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnaddperson = findViewById(R.id.btnaddperson);
        flotbtnaddperson = findViewById(R.id.flotbtnaddperson);
        btnsearch = findViewById(R.id.btnsearch);

        recyclerView = findViewById(R.id.personslist);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personList = fetchPersonsFromDatabase();
        if (personList.isEmpty()) {
            flotbtnaddperson.setVisibility(View.VISIBLE);
        } else {
            flotbtnaddperson.setVisibility(View.GONE);
            adapter = new PersonAdapter(personList, personName -> {
                Intent intent = new Intent(BaseMainActivity.this, LogListActivity.class);
                intent.putExtra("person_name", personName);
                startActivity(intent);
            });
            recyclerView.setAdapter(adapter);
        }

        btnaddperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseMainActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        });

        flotbtnaddperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseMainActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        });

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BaseMainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Person> fetchPersonsFromDatabase() {
        List<Person> persons = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("person", new String[]{"name", "age"}, null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
                persons.add(new Person(name, age));
            }
            cursor.close();
        }
        db.close();

        return persons;
    }
}