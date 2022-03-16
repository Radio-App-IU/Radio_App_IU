package com.example.radio_app_iu

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatenbankKlasse(context: Context): SQLiteOpenHelper(context, "Bewertungen", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE [Bewertungen]" +
                "([id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "[Bewertungstext] TEXT NOT NULL, [Nickname] TEXT NOT NULL," +
                "[Rating] TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, versionAlt: Int, versionNeu: Int) {
    }
}