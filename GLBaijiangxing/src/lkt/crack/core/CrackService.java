package lkt.crack.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class CrackService extends Service {
	private FileObserver mFo;
	public static final String DIR_NAME_DD_AD = "ddcaches";
	public static final String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final String AD_PATH = ROOT_PATH + "/" + DIR_NAME_DD_AD;
	private HandlerThread mThread;
	private ServiceHandler mHandler;

	private PackageManager pm;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		pm = getPackageManager();
		mThread = new HandlerThread("abc");
		mThread.start();
		mHandler = new ServiceHandler(mThread.getLooper());

		File dirPath = new File(AD_PATH);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		mFo = new FileObserver(dirPath.getAbsolutePath()) {
			@Override
			public void onEvent(int event, String path) {
				switch (event) {
				case FileObserver.CLOSE_WRITE:
					Log.e("lkt", "send path:" + path);
					mHandler.sendMessage(mHandler.obtainMessage(100, path));
					break;
				default:
					break;
				}
			}
		};
		mFo.startWatching();
	}

	private List<String> pkgSent = new ArrayList<String>();

	private long last_waiting_time = 0;

	class ServiceHandler extends Handler {

		public ServiceHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 100) {
				String path = msg.obj.toString();
				if (!pkgSent.contains(path)) {
					pkgSent.add(path);
					if (last_waiting_time == 0 || System.currentTimeMillis() - last_waiting_time > 5000) {
						try {
							Thread.sleep(new Random().nextInt(10000) + 5000);
							last_waiting_time = System.currentTimeMillis();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					PackageInfo pi = pm.getPackageArchiveInfo(AD_PATH + "/" + path, 1);
					if (pi != null) {
						String pkgname = pi.packageName;
						Uri uri = Uri.parse("package:" + pkgname);
						Intent intent = new Intent();
						intent.setAction("android.intent.action.PACKAGE_DDDDD");
						intent.setData(uri);
						sendBroadcast(intent);
					}
				}
			}
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return START_STICKY;
	}

	@Override
	public String getPackageName() {
		return super.getPackageName();
	}

}
