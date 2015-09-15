package lkt.crack.core;

import android.util.Log;

public class StringBytesAlterationUtil {
	/**
	 * 为了容易操作，只操作字符串长度一样的修改
	 * 
	 * @param source
	 * @param dest
	 */
	public static void alter(String source, String dest, byte[] fBytes) {
		if (fBytes != null && source != null && dest != null && dest.length() == source.length()) {
			byte[] srcStrBytes = source.getBytes();
			byte[] destStrBytes = dest.getBytes();
			int searchLenth = fBytes.length - srcStrBytes.length;
			boolean found = false;
			int start_index_of_srcStr = -1;
			for (int i = 0; i < searchLenth; i++) {
				found = true;
				for (int j = 0; j < srcStrBytes.length; j++) {
					if (fBytes[i + j] != srcStrBytes[j]) {
						found = false;
						break;
					}
				}
				if (found) {
					start_index_of_srcStr = i;
					Log.e("lkt", "string found ,index : " + start_index_of_srcStr);
					break;
				}
			}
			if (start_index_of_srcStr != -1) {
				for (int i = 0; i < destStrBytes.length; i++) {
					fBytes[start_index_of_srcStr + i] = destStrBytes[i];
				}
			}
		}
	}

}
