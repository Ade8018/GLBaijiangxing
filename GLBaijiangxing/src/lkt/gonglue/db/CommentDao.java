package lkt.gonglue.db;

import java.util.ArrayList;
import java.util.List;

import lkt.gonglue.entity.Comment;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CommentDao {
	private static SQLiteDatabase sDb;

	public static void init(Context context) {
		if (sDb == null) {
			sDb = new DatabaseOpenHelper(context, "gonglue.db", null, 1)
					.getWritableDatabase();
		}
	}

	public static long insert(Comment c) {
		ContentValues values = new ContentValues();
		values.put("name", "");
		values.put("comment", c.text);
		return sDb.insert("comment", null, values);
	}

	public static List<Comment> query() {
		List<Comment> cs = new ArrayList<Comment>();
		Cursor cur = sDb.query("comment", null, null, null, null, null, null);
		Comment c = null;
		while (cur.moveToNext()) {
			c = new Comment("", cur.getString(1));
			cs.add(c);
		}
		cur.close();
		return cs;
	}
}
