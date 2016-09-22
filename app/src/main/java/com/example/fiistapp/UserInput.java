package com.example.fiistapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by Shirin on 9/21/2016.
 */

public class UserInput extends Activity {
    final static String TABLE_NAME = "artists";
    final static String ARTIST_NAME = "name";
    final static String _ID = "_id";
    private SQLiteDatabase db = null;
    private DatabaseOpenHelper dbHelper = null;
    EditText artName;
    String artist_name;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_input);

        artName = (EditText) findViewById(R.id.art_name); //get the edittext field

        dbHelper = new DatabaseOpenHelper(this); //open database
        db = dbHelper.getWritableDatabase(); //to write in the database
    }

    public void saveTodb(View v){

        ContentValues values = new ContentValues();

        //get the names from user input
        artist_name = artName.getText().toString();
        //System.out.println(artist_name);

        //save it to the database
        values.put(ARTIST_NAME, artist_name);
        db.insert(TABLE_NAME, null, values);

        Toast.makeText(getBaseContext(),"Name Added", Toast.LENGTH_LONG).show();

        //and show the name in the listview
        Intent intent = new Intent(this, DatabaseExampleActivity.class);
        startActivity(intent);
    }

}
