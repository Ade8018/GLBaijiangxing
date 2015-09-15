package lkt.crack.util;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;

public class SpHelper {
	public static final String SP_NAME_MAIN = "main_lkt_crack_sp";
	public static final String SP_KEY_CHEAT_PKG_NAME = "cheat_pkg_name";
	public static final String SP_KEY_DOWNLOADED_PKG_NAMES = "downloaded_pkg_names";
	public static final String SP_KEY_CLICKED_PKG_NAMES = "clicked_pkg_names";
	public static final String SP_KEY_SENT_PKG_NAMES = "sent_pkg_names";
	private static Context sContext;
	private static SharedPreferences sp;

	private SpHelper() {
	}

	public static void init(Context context) {
		sContext = context;
		sp = sContext.getSharedPreferences(SP_NAME_MAIN, Context.MODE_PRIVATE);
	}

	public static String getCheatPkgName() {
		if (sp != null) {
			return sp.getString(SP_KEY_CHEAT_PKG_NAME, null);
		}
		return null;
	}

	public static boolean saveCheatPkgName(String pkgName) {
		if (pkgName != null) {
			if (sp != null) {
				return sp.edit().putString(SP_KEY_CHEAT_PKG_NAME, pkgName)
						.commit();
			}
		}
		return false;
	}

	public static Set<String> getDownloadedPkgs() {
		if (sp != null) {
			return sp.getStringSet(SP_KEY_DOWNLOADED_PKG_NAMES,
					new HashSet<String>());
		}
		return null;
	}

	public static Set<String> getClickedPkgs() {
		if (sp != null) {
			return sp.getStringSet(SP_KEY_CLICKED_PKG_NAMES,
					new HashSet<String>());
		}
		return null;
	}

	public static void saveDownLoadedPkgs(Set<String> pkgs) {
		if (sp != null) {
			sp.edit().putStringSet(SP_KEY_DOWNLOADED_PKG_NAMES, pkgs).commit();
		}
	}

	public static void saveClickedPkgs(Set<String> pkgs) {
		if (sp != null) {
			sp.edit().putStringSet(SP_KEY_CLICKED_PKG_NAMES, pkgs).commit();
		}
	}

	public static Set<String> getSentPkgs() {
		if (sp != null) {
			return sp
					.getStringSet(SP_KEY_SENT_PKG_NAMES, new HashSet<String>());
		}
		return null;
	}

	public static void saveSentPkgs(Set<String> pkgs) {
		if (sp != null) {
			sp.edit().putStringSet(SP_KEY_SENT_PKG_NAMES, pkgs).commit();
		}
	}

}
