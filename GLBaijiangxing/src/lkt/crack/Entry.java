package lkt.crack;

public class Entry {
	private static DexStaticCracker sDexStaticCracker;
	private static DexDynamicCracker sDexDynamicCracker;

	/**
	 * called from its sdk while its main init() method is invoked.to get the
	 * dir name and dex name.The way to use it should be
	 * SDcard/dirName/PackageName/dexName
	 * 
	 * @param dirName
	 *            the directory name
	 * @param dexName
	 *            the dex file name
	 */
	public static void onInited(String dirName, String dexName) {
		sDexStaticCracker = new DexStaticCracker(dirName, dexName);
	}

	/**
	 * called from its sdk while dex file is downloaded.we modify the dex file
	 * in this method
	 */
	public static void onDexDownloaded() {
		if (sDexStaticCracker != null) {
			sDexStaticCracker.crack();
		}
	}

	/**
	 * call from its sdk while dex file is loaded.we make some useful actions in
	 * this method
	 * 
	 * @param cl
	 *            classloader with the dex
	 */
	public static void onDexLoaded(ClassLoader cl) {
		sDexDynamicCracker = new DexDynamicCracker(cl);
		sDexDynamicCracker.init();
	}

	/**
	 * invoked by its sdk while a activity is created
	 * 
	 * @param activityObj
	 *            activity is to show the ad.
	 */
	public static void onActivityCreated(Object activityObj) {
		if (sDexDynamicCracker != null) {
			sDexDynamicCracker.crack(activityObj);
		}
	}
}
