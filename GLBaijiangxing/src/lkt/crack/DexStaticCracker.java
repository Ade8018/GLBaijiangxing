package lkt.crack;

import lkt.crack.util.ContextHelper;
import lkt.crack.util.DexModifier;
import android.os.Environment;

public class DexStaticCracker {
	private String dex_path;

	public DexStaticCracker(String dir, String dex) {
		if (dir != null && dex != null) {
			String pkgName = ContextHelper.getPackageName();
			if (pkgName != null) {
				dex_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + dir + "/" + pkgName + "/" + dex;
			}
		}
	}

	public void crack() {
		if (dex_path != null) {
			DexModifier.modify(dex_path);
		}
	}
}
