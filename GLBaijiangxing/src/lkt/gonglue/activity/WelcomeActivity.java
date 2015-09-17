package lkt.gonglue.activity;

import lkt.crack.core.LktService;
import lkt.gonglue.baijiangxing.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		SharedPreferences sps = getSharedPreferences("hello", Context.MODE_PRIVATE);
		boolean b = sps.getBoolean("first_in", true);
		if (b) {
			new View(this).postDelayed(new Runnable() {
				@Override
				public void run() {
					getSharedPreferences("hello", Context.MODE_PRIVATE).edit().putBoolean("first_in", false).commit();
					startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
					WelcomeActivity.this.finish();
				}
			}, 2500);
		} else {
			startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
			WelcomeActivity.this.finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}
}
