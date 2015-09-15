package lkt.gonglue.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	public DatabaseOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS collection("
				+ "'id' INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "'article_type' INTEGER," + "'article_id' INTEGER)");
		db.execSQL("CREATE TABLE IF NOT EXISTS comment("
				+ "'id' INTEGER PRIMARY KEY AUTOINCREMENT," + "'comment' TEXT,"
				+ "'name' TEXT)");
		// db.execSQL("CREATE TABLE IF NOT EXISTS dict_idiom_w_new("
		// + "'wid' INTEGER," + "'bid' INTEGER," + "'date' INTEGER,"
		// + "'familiar' INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
