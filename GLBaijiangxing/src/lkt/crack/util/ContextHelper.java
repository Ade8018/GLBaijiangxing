package lkt.crack.util;

import lkt.crack.core.LktService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

public class ContextHelper {
	private static Context sContext;
	private static PackageManager sPm;

	private ContextHelper() {
	}

	public static void init(Context context) {
		sContext = context;
		if (sContext != null) {
			sPm = sContext.getPackageManager();
		}
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

	public static String getApkFilePackageName(String path) {
		Log.e("lkt", "getApkFilePackageName");
		String pkgName = null;
		if (sPm != null) {
			Log.e("lkt", "sPm != null");
			PackageInfo pi = sPm.getPackageArchiveInfo(path, 1);
			if (pi != null) {
				Log.e("lkt", "pi != null");
				pkgName = pi.packageName;
			}
		}
		return pkgName;
	}
}
