package lkt.crack.core;

import java.util.Set;

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
		if (downloadedPkgs != null) {
			downloadedPkgs.add(pkg);
			onPackageAdded();
		}
	}

	public void onPackageClicked(String pkg) {
		if (clickedPkgs != null) {
			downloadedPkgs.add(pkg);
			onPackageAdded();
		}
	}

	public void onDestory() {
		SpHelper.saveClickedPkgs(clickedPkgs);
		SpHelper.saveDownLoadedPkgs(downloadedPkgs);
	}
	
	public void onPackageAdded(){
		
	}

}
