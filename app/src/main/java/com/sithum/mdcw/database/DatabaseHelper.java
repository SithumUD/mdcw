package com.sithum.mdcw.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "personDetails.db";
    private static final int DATABASE_VERSION = 2;  // Incremented version to handle database upgrade
    private static final String TABLE_NAME = "person";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_BLOOD_TYPE = "blood_type";
    private static final String COLUMN_PROFILE_IMAGE = "profile_image";  // New column for profile image

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table with the new column for profile image
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_BIRTHDAY + " TEXT, "
                + COLUMN_AGE + " INTEGER, "
                + COLUMN_GENDER + " TEXT, "
                + COLUMN_BLOOD_TYPE + " TEXT, "
                + COLUMN_PROFILE_IMAGE + " BLOB)";  // BLOB column for image
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // If the database version is upgraded, drop the old table and create the new one
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COLUMN_PROFILE_IMAGE + " BLOB");
        }
    }

    // Method to insert a new person record with an image
    public boolean insertPerson(String name, String birthday, int age, String gender, String bloodType, byte[] profileImage) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_BIRTHDAY, birthday);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_GENDER, gender);
        values.put(COLUMN_BLOOD_TYPE, bloodType);
        values.put(COLUMN_PROFILE_IMAGE, profileImage);  // Insert the image as BLOB

        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;  // Return true if the record is successfully inserted
    }
}
