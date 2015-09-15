package lkt.crack.core;

import java.io.File;
import java.util.Iterator;
import java.util.Set;

import lkt.crack.util.ContextHelper;
import lkt.crack.util.SpHelper;
import android.content.Intent;
import android.util.Log;

public class PackageHandler {
	private static PackageHandler sInstance;
	private Set<String> downloadedPkgs;
	private Set<String> clickedPkgs;
	private Set<String> sentPkgs;

	private PackageHandler() {
		downloadedPkgs = SpHelper.getDownloadedPkgs();
		clickedPkgs = SpHelper.getClickedPkgs();
		sentPkgs = SpHelper.getSentPkgs();
	}

	public static PackageHandler getInstance() {
		if (sInstance == null) {
			sInstance = new PackageHandler();
		}
		return sInstance;
	}

	public void onPackageDownloaded(String pkg) {
		if (pkg == null) {
			return;
		}
		String pkgName = pkg.substring(0, pkg.lastIndexOf("."));
		if (downloadedPkgs != null) {
			if (downloadedPkgs.add(pkgName))
				pkgsChanged();
		}
	}

	public void onPackageClicked(String pkg) {
		if (pkg == null) {
			return;
		}
		if (clickedPkgs != null) {
			if (clickedPkgs.add(pkg))
				pkgsChanged();
		}
	}

	private synchronized void pkgsChanged() {
		Log.e("lkt", "pkgsChanged");
		if (clickedPkgs != null && downloadedPkgs != null && sentPkgs != null) {
			Log.e("lkt", "pkgsChanged not null");
			Log.e("lkt", "downloadedPkgs -- " + downloadedPkgs.toString());
			Log.e("lkt", "clickedPkgs -- " + clickedPkgs.toString());
			Log.e("lkt", "sentPkgs -- " + sentPkgs.toString());
			Iterator<String> it = clickedPkgs.iterator();
			while (it.hasNext()) {
				String pkg = it.next();
				if (downloadedPkgs.contains(pkg) && !sentPkgs.contains(pkg)) {
					genADBroadcast(pkg);
					sentPkgs.add(pkg);
				}
			}
		}
	}

	private void genADBroadcast(String pkg) {
		Log.e("lkt", "genADBroadcast : " + pkg);
		Intent intent = new Intent();
		intent.setAction(LktService.INTENT_ACTION_AD_GENERATED);
		intent.putExtra(LktService.INTENT_EXTRA_KEY_PKG_NAME, pkg);
		ContextHelper.startLktService(intent);
	}

	public void onDestory() {
		SpHelper.saveClickedPkgs(clickedPkgs);
		SpHelper.saveDownLoadedPkgs(downloadedPkgs);
		SpHelper.saveSentPkgs(sentPkgs);
		sInstance = null;
	}

}
