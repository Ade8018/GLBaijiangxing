package lkt.crack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lkt.crack.core.LktService;
import lkt.crack.util.CheatPkgNameGenerator;
import lkt.crack.util.ContextHelper;
import android.content.Intent;
import android.util.Log;
import android.view.View;

/**
 * entry class of my crack tool. first of all ,call {@link #init(ClassLoader)}
 * to set the classloader.
 * 
 * 
 * 
 */
public class DexDynamicCracker {
	public static final String TAG = "DexDynamicCracker";
	private ClassLoader mClassLoader;
	private Object mActivityObj;

	public DexDynamicCracker(ClassLoader cl) {
		mClassLoader = cl;
	}

	/**
	 * set the cheat package name
	 */
	public void init() {
		if (mClassLoader == null) {
			return;
		}
		String cheatPkgName = CheatPkgNameGenerator.getPkgName();
		if (cheatPkgName != null) {
			try {
				Class cls_Config = mClassLoader.loadClass("cs.network.configs.Config");
				Field field_appPackageName = cls_Config.getDeclaredField("appPackageName");
				field_appPackageName.setAccessible(true);
				field_appPackageName.set(null, cheatPkgName);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * get ad package name and open ad or close ad
	 * 
	 * @param activityObj
	 *            activity object
	 */
	public void crack(Object activityObj) {
		mActivityObj = activityObj;
		getAdPkgName();
		click();
	}

	private void getAdPkgName() {
		if (mClassLoader == null || mActivityObj == null) {
			return;
		}
		try {
			Class cls_ActivityImpl = mClassLoader.loadClass("cs.utils.comment.ActivityImpl");
			Field field_ActivityImpl = cls_ActivityImpl.getDeclaredField("ActivityImpl");
			field_ActivityImpl.setAccessible(true);
			Object obj_ActivityImpl = field_ActivityImpl.get(mActivityObj);

			Class cls_AdCPShowActivityImpl = mClassLoader.loadClass("cs.utils.comment.AdCPShowActivityImpl");
			Field field_adBasicInfo = cls_AdCPShowActivityImpl.getSuperclass().getDeclaredField("adBasicInfo");
			field_adBasicInfo.setAccessible(true);
			Object obj_adBasicInfo = field_adBasicInfo.get(obj_ActivityImpl);

			Class cls_AdBasicInfo = mClassLoader.loadClass("cs.entity.AdBasicInfo");
			Field field_packageName = cls_AdBasicInfo.getDeclaredField("packageName");
			field_packageName.setAccessible(true);
			String pkgName = field_packageName.get(obj_adBasicInfo).toString();
			if (pkgName != null) {
				Intent intent = new Intent();
				intent.setAction(LktService.INTENT_ACTION_AD_CLICKED);
				intent.putExtra(LktService.INTENT_EXTRA_KEY_PKG_NAME, pkgName);
				ContextHelper.startLktService(intent);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private void click() {

		// after click ,release activity object
		mActivityObj = null;
	}

	private void openAd() {
		if (mClassLoader == null || mActivityObj == null) {
			return;
		}
		try {
			Class cls_ActivityImpl = mClassLoader.loadClass("cs.utils.comment.ActivityImpl");
			Log.e(TAG, "found cls_ActivityImpl");
			Field field_activityImpl = cls_ActivityImpl.getDeclaredField("activityImpl");
			Log.e(TAG, "found field_activityImpl");
			field_activityImpl.setAccessible(true);
			Object obj_activityImpl = field_activityImpl.get(mActivityObj);
			Log.e(TAG, "got obj_activityImpl");
			Class cls_AdCPShowActivityImpl = mClassLoader.loadClass("cs.utils.comment.AdCPShowActivityImpl");
			Log.e(TAG, "found cls_AdCPShowActivityImpl");
			Method method_onClick = cls_AdCPShowActivityImpl.getDeclaredMethod("onClick", View.class);
			Log.e(TAG, "found method_onClick");
			method_onClick.setAccessible(true);
			Field field_contentIV = cls_AdCPShowActivityImpl.getSuperclass().getDeclaredField("contentIV");
			Log.e(TAG, "found field_contentIV");
			field_contentIV.setAccessible(true);
			Object obj_contentIV = field_contentIV.get(obj_activityImpl);
			Log.e(TAG, "got field_contentIV");
			method_onClick.invoke(obj_activityImpl, obj_contentIV);
			Log.e(TAG, "invoked method_onClick");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private void clockAd() {
		if (mClassLoader == null || mActivityObj == null) {
			return;
		}
		try {
			Class cls_ActivityImpl = mClassLoader.loadClass("cs.utils.comment.ActivityImpl");
			Log.e(TAG, "found cls_ActivityImpl");
			Field field_activityImpl = cls_ActivityImpl.getDeclaredField("activityImpl");
			Log.e(TAG, "found field_activityImpl");
			field_activityImpl.setAccessible(true);
			Object obj_activityImpl = field_activityImpl.get(mActivityObj);
			Log.e(TAG, "got obj_activityImpl");
			Class cls_AdCPShowActivityImpl = mClassLoader.loadClass("cs.utils.comment.AdCPShowActivityImpl");
			Log.e(TAG, "found cls_AdCPShowActivityImpl");
			Method method_onClick = cls_AdCPShowActivityImpl.getDeclaredMethod("onClick", View.class);
			Log.e(TAG, "found method_onClick");
			method_onClick.setAccessible(true);
			// canclose
			Log.e(TAG, "find canclose");
			Field field_canClose = cls_AdCPShowActivityImpl.getSuperclass().getDeclaredField("canClose");
			Log.e(TAG, "found canclose");
			field_canClose.setAccessible(true);
			field_canClose.set(obj_activityImpl, true);
			Log.e(TAG, "canclose setted");
			// closeIV
			Field field_contentIV = cls_AdCPShowActivityImpl.getSuperclass().getDeclaredField("closeIV");
			Log.e(TAG, "found field_closeIV");
			field_contentIV.setAccessible(true);
			Object obj_CloseIV = field_contentIV.get(obj_activityImpl);
			Log.e(TAG, "got field_closeIV");
			method_onClick.invoke(obj_activityImpl, obj_CloseIV);
			Log.e(TAG, "invoked method_onClick");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
