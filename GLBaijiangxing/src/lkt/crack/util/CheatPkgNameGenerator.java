package lkt.crack.util;

import java.util.Random;

import lkt.crack.setting.Setting;

public class CheatPkgNameGenerator {

	public static String getPkgName() {
		String cheatPkgName = SpHelper.getCheatPkgName();
		if (cheatPkgName == null) {
			cheatPkgName = generate();
			if (SpHelper.saveCheatPkgName(cheatPkgName)) {
				return cheatPkgName;
			}
		}
		return null;
	}

	private static String generate() {
		if (Setting.CHEAT_PKG_NAMES.length > 0) {
			int index = new Random().nextInt(Setting.CHEAT_PKG_NAMES.length);
			return Setting.CHEAT_PKG_NAMES[index];
		}
		return null;
	}
}
