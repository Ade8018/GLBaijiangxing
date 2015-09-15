package lkt.crack.util;

import android.app.NotificationManager;

public class NotificationHelper {
	private static NotificationManager sNotificationManager;

	private NotificationHelper() {
	}

	public static void init(NotificationManager manager) {
		sNotificationManager = manager;
	}

	public static void cancelAllNotification() {
		if (sNotificationManager != null) {
			sNotificationManager.cancelAll();
		}
	}
}
