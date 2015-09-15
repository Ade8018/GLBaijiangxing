package lkt.gonglue.common;

import android.app.Activity;

public class WindowManager {
	public static final int WINDOW_TYPE_COMMENT = 0;

	public synchronized static BaseWindow getWindow(Activity act,
			int window_type) {
		switch (window_type) {
		case WINDOW_TYPE_COMMENT:
			return new CommentWindow(act);
		default:
			return null;
		}
	}
}
