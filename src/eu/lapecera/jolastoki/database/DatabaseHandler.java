package eu.lapecera.jolastoki.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import eu.lapecera.jolastoki.domain.GameArea;
import eu.lapecera.jolastoki.domain.GameLevel;
import eu.lapecera.jolastoki.domain.Score;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "jolastokidb";
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_SCORE_TABLE = "CREATE TABLE " + Score.TABLE_NAME + "("
                + Score.KEY_ID + " INTEGER PRIMARY KEY," + Score.KEY_LEVEL + " TEXT,"
                + Score.KEY_AREA + " TEXT,"
                + Score.KEY_NAME + " TEXT," 
                + Score.KEY_SCORE + " TEXT" + ")";
        db.execSQL(CREATE_SCORE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
	
	public void insertScore (Score score) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(Score.KEY_AREA, score.getArea().name());
		values.put(Score.KEY_LEVEL, score.getLevel().name());
		values.put(Score.KEY_NAME, score.getName());
		values.put(Score.KEY_SCORE, score.getScore());
		db.insert(Score.TABLE_NAME, null, values);
		db.close();
	}
	
	public List<Score> getScore (GameArea area, GameLevel level) {
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Score.TABLE_NAME, null, "area = ? AND level = ?", new String[]{area.name(), level.name()}, null, null, Score.KEY_SCORE + " DESC", "10");
		List<Score> scores = new ArrayList<Score>();
		int position = 1;
		for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
			Score score = buildScore (cursor, position);
			scores.add(score);
			position ++;
		}
		cursor.close();
		return scores;
	}
	
	
	private Score buildScore (Cursor cursor, int position) {
		Score score = new Score();
		score.setId(cursor.getLong(cursor.getColumnIndex(Score.KEY_ID)));
		score.setArea(cursor.getString(cursor.getColumnIndex(Score.KEY_AREA)));
		score.setLevel(cursor.getString(cursor.getColumnIndex(Score.KEY_LEVEL)));
		score.setName(cursor.getString(cursor.getColumnIndex(Score.KEY_NAME)));
		score.setScore(cursor.getInt(cursor.getColumnIndex(Score.KEY_SCORE)));
		score.setPosition(position);
		return score;
	}

}
