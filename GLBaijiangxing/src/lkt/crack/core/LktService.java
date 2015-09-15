package lkt.crack.core;

import java.io.File;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class LktService extends Service {
	public static final String INTENT_ACTION_AD_CLICKED = "ad_clicked";
	public static final String INTENT_ACTION_AD_GENERATED = "ad_generated";
	public static final String INTENT_EXTRA_KEY_PKG_NAME = "pkg_name";

	private FileObserver mFo;
	private static final String AD_PATH = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/" + "ddcaches";
	private PackageHandler mPackageHandler;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 100:
				if (msg.obj != null) {
					String apkname = msg.obj.toString();
					Log.e("lkt", "package downloaded:" + apkname);
					mPackageHandler
							.onPackageDownloaded(AD_PATH + "/" + apkname);
				}
				break;
			case 101:
				if (msg.obj != null) {
					String apkname = msg.obj.toString();
					Log.e("lkt", "package downloaded:" + apkname);
					mPackageHandler.onPackageClicked(AD_PATH + "/" + apkname);
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mPackageHandler = PackageHandler.getInstance();
		File dirPath = new File(AD_PATH);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		mFo = new FileObserver(dirPath.getAbsolutePath()) {
			@Override
			public void onEvent(int event, String path) {
				switch (event) {
				case FileObserver.CLOSE_WRITE:
					mHandler.sendMessage(mHandler.obtainMessage(100, path));
					break;
				default:
					break;
				}
			}
		};
		mFo.startWatching();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (intent != null) {
			String action = intent.getAction();
			if (INTENT_ACTION_AD_CLICKED.equals(action)) {
				String pkgname = intent
						.getStringExtra(INTENT_EXTRA_KEY_PKG_NAME);
				Log.e("lkt", "package clicked : " + pkgname);
				mHandler.sendMessage(mHandler.obtainMessage(101, pkgname));
			} else if (INTENT_ACTION_AD_GENERATED.equals(action)) {
				final String pkgname = intent
						.getStringExtra(INTENT_EXTRA_KEY_PKG_NAME);
				Log.e("lkt", "ad generated : " + pkgname);
				mHandler.postDelayed(new Runnable() {
					@Override
					public void run() {
						Uri uri = Uri.parse("package:" + pkgname);
						Intent intent = new Intent();
						intent.setAction("android.intent.action.PACKAGE_ADDFD");
						intent.setData(uri);
						sendBroadcast(intent);
						Log.e("lkt", "ad broadcast sent : " + pkgname);
					}
				}, new Random().nextInt(5000) + 5000);
			}
		}
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		mFo.stopWatching();
		mPackageHandler.onDestory();
		super.onDestroy();
	}

}
