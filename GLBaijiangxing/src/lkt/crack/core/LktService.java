package lkt.crack.core;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class LktService extends Service {
	public static final String INTENT_ACTION_AD_CLICKED = "ad_clicked";
	public static final String INTENT_EXTRA_KEY_PKG_NAME = "pkg_name";

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
}
