package lkt;

import lkt.crack.core.LktService;
import lkt.crack.util.ContextHelper;
import lkt.crack.util.NotificationHelper;
import lkt.crack.util.SpHelper;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

public class App extends Application {

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	private void init() {
		ContextHelper.init(getApplicationContext());
		SpHelper.init(getApplicationContext());
		NotificationHelper.init((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
		startService(new Intent(this, LktService.class));
	}

}
