package com.example.fiistapp;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class DatabaseExampleActivity extends ListActivity {
	final static String TABLE_NAME = "artists";
	final static String ARTIST_NAME = "name";
	final static String _ID = "_id";
	final static String[] columns = { _ID, ARTIST_NAME };
	private SQLiteDatabase db = null;
	private DatabaseOpenHelper dbHelper = null;
	Button btn_update;
	ImageButton btn_delete;
	TextView id,name_art;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		btn_delete = (ImageButton) findViewById(R.id.delete);
		btn_update = (Button) findViewById(R.id.update);

		id = (TextView) findViewById(R.id._id);
		//final int myid = Integer.parseInt(id.toString());
		//name_art = (TextView) findViewById(R.id.name);

		dbHelper = new DatabaseOpenHelper(this);
		db = dbHelper.getWritableDatabase(); //to write in the database

		btn_delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteRow();
			}
		});

		//insertArtists();

		// Sorry Lady Gaga :-(
		//delete(Integer.parseInt(_ID));

		// fix the Man in Black
		updateArtist();

		// read artist and display them 
		Cursor c = readArtists();
		setListAdapter(new SimpleCursorAdapter(this, R.layout.list_layout, c,
				columns, new int[] { R.id._id, R.id.name }));

	}

	private void insertArtists() {

		/**ContentValues values = new ContentValues();

		values.put(ARTIST_NAME, name);
		db.insert(TABLE_NAME, null, values);**/

		/**values.put(ARTIST_NAME, "Frank Sinatra");
		db.insert(TABLE_NAME, null, values);

		values.clear();

		values.put(ARTIST_NAME, "Lady Gaga");
		db.insert(TABLE_NAME, null, values);

		values.clear();

		values.put(ARTIST_NAME, "Jawny Cash");
		db.insert(TABLE_NAME, null, values);

		values.clear();

		values.put(ARTIST_NAME, "Ludwig von Beethoven");
		db.insert(TABLE_NAME, null, values);**/
	}

	private Cursor readArtists() {
		return db.query(TABLE_NAME, columns, null, new String[] {}, null, null,
				null);
	}

	/**private int deleteArtist(View v) {
		return db.delete(TABLE_NAME, ARTIST_NAME + "=?",
				new String[] {"Lady Gaga"});
	}**/
	public void deleteRow(int del_id){
		//Cursor c = readArtists();
		//db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + ARTIST_NAME + " = " + new String[]{});
		//db.delete(TABLE_NAME,_ID + "=?", new String[]{String.valueOf(this.id)});

		//String del_id = id.getTag().toString();
		db.delete(TABLE_NAME, _ID + "=?", new String[] {String.valueOf(del_id)});

	}

	private int updateArtist() {
		ContentValues values = new ContentValues();
		values.put(ARTIST_NAME, "Johnny Cash");
		return db.update(TABLE_NAME, values, ARTIST_NAME + "=?",
				new String[] { "Jawny Cash" });
	}

	@Override
	protected void onDestroy() {
		db.close();
		super.onDestroy();
	}

}