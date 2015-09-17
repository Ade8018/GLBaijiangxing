package lkt.crack.core;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * activity to handle the installation request
 * 
 */
public class TypeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.e("lkt", "TypeActivity");
		finish();
	}
}
