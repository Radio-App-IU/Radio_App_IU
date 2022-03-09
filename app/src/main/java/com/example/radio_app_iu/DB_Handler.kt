package com.example.radio_app_iu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME = "zAPP-DB"
    val TABLE_NAME = "Songs"
    val COLUMN_TITLE = "title"
    val COLUMN_INTERPRET = "interpret"
    val COLUMN_ALBUM = "album"

    //creating DB-Handler(context, DB-Name, DB-Factory, version; has to implement two methods)
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

     //will be executed, when Device doesn't contain the DB
     override fun onCreate(db: SQLiteDatabase?) {
         val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                 COLUMN_TITLE + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                 COLUMN_INTERPRET + " VARCHAR(100), " +
                 COLUMN_ALBUM + " VARCHAR(100)"

         db?.execSQL(createTable)
     }

     override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
            TODO("Not yet implemented")
        }

     fun insertData(song : Song){
         val db = this.writableDatabase
         var cv = ContentValues()
         cv.put(COLUMN_TITLE, song.title)
         cv.put(COLUMN_INTERPRET, song.interpret)
         cv.put(COLUMN_ALBUM, song.album)
         var result = db.insert(TABLE_NAME, null, cv)

         if(result == -1.toLong())
             Toast.makeText(context, "Insertion of the data failed!", Toast.LENGTH_SHORT).show()
         else
             Toast.makeText(context, "Insertion was a success!", Toast.LENGTH_SHORT).show()
     }
}