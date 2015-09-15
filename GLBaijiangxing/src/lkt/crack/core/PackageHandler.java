package lkt.crack.core;

import java.util.Set;

import android.content.Intent;

import lkt.crack.util.ContextHelper;
import lkt.crack.util.SpHelper;

public class PackageHandler {
	private static PackageHandler sInstance;
	private Set<String> downloadedPkgs;
	private Set<String> clickedPkgs;

	private PackageHandler() {
		downloadedPkgs = SpHelper.getDownloadedPkgs();
		clickedPkgs = SpHelper.getClickedPkgs();
	}

	public static PackageHandler getInstance() {
		if (sInstance != null) {
			sInstance = new PackageHandler();
		}
		return sInstance;
	}

	public void onPackageDownloaded(String pkg) {
		if (pkg == null) {
			return;
		}
		if (clickedPkgs != null && clickedPkgs.contains(pkg)) {
			genADBroadcast(pkg);
			clickedPkgs.remove(pkg);
			return;
		}
		if (downloadedPkgs != null) {
			downloadedPkgs.add(pkg);
		}
	}

	public void onPackageClicked(String pkg) {
		if (pkg == null) {
			return;
		}
		if (downloadedPkgs != null && downloadedPkgs.contains(pkg)) {
			genADBroadcast(pkg);
			downloadedPkgs.remove(pkg);
			return;
		}
		if (clickedPkgs != null) {
			downloadedPkgs.add(pkg);
		}
	}

	private void genADBroadcast(String pkg) {
		Intent intent = new Intent();
		intent.setAction(LktService.INTENT_ACTION_AD_GENERATED);
		intent.putExtra(LktService.INTENT_EXTRA_KEY_PKG_NAME, pkg);
		ContextHelper.startLktService(intent);
	}

	public void onDestory() {
		SpHelper.saveClickedPkgs(clickedPkgs);
		SpHelper.saveDownLoadedPkgs(downloadedPkgs);
		sInstance = null;
	}

}
