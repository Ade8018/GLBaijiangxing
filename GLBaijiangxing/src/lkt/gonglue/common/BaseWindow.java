package lkt.gonglue.common;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

public abstract class BaseWindow implements WindowController {
	private PopupWindow mPop;
	Activity mAct;
	View mView;

	public BaseWindow(Activity mAct, int layout) {
		this.mAct = mAct;
		inflateView(layout);
		mPop = new PopupWindow(mView, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);
		mPop.setFocusable(true);
		mPop.setBackgroundDrawable(new BitmapDrawable());
		mPop.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
		mPop.setSoftInputMode(LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		mPop.setAnimationStyle(android.R.style.Animation_InputMethod);
	}

	private void inflateView(int layout) {
		mView = LayoutInflater.from(mAct).inflate(layout, null, false);
	}

	@Override
	public boolean isShowing() {
		if (mPop != null) {
			return mPop.isShowing();
		}
		return false;
	}

	@Override
	public void show() {
		if (mPop != null) {
			if (!mPop.isShowing()) {
				mPop.showAtLocation(mView, Gravity.CENTER, 0, 0);
				afterShowing();
			}
		}
	}

	@Override
	public void dismiss() {
		if (mPop != null && mPop.isShowing()) {
			mPop.dismiss();
		}
	}

	abstract void afterShowing();
}
