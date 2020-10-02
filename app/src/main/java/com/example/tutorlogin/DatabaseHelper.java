package com.example.tutorlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TUTOR_TABLE = "TUTOR_TABLE";
    public static final String COLUMN_TUTOR_NAME = "tutor_name";
    public static final String COLUMN_TUTOR_ROLE = "tutor_role";
    public static final String COLUMN_TUTOR_SUBJECT = "tutor_subject";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "tutor.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement =
                "CREATE TABLE " + TUTOR_TABLE + " (" +
                        "id_integer PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TUTOR_NAME + " TEXT, " +
                        COLUMN_TUTOR_ROLE + " TEXT," +
                        COLUMN_TUTOR_SUBJECT + " TEXT)";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TutorModel tutorModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TUTOR_NAME, tutorModel.getName());
        cv.put(COLUMN_TUTOR_ROLE, tutorModel.getRole());
        cv.put(COLUMN_TUTOR_SUBJECT, tutorModel.getSubject());

        long insert = db.insert(TUTOR_TABLE, null, cv);

        return insert != -1;


    }
}
