package lkt.crack.util;

import lkt.crack.core.LktService;
import android.content.Context;
import android.content.Intent;

public class ContextHelper {
	private static Context sContext;

	private ContextHelper() {
	}

	public static void init(Context context) {
		sContext = context;
	}

	public static void startLktService(Intent intent) {
		if (sContext != null && intent != null) {
			intent.setClass(sContext, LktService.class);
			sContext.startService(intent);
		}
	}

	public static Context getContext() {
		return sContext;
	}

	public static String getPackageName() {
		if (sContext != null) {
			return sContext.getPackageName();
		}
		return null;
	}
}
