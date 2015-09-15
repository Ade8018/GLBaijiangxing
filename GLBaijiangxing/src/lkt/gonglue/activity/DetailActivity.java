package lkt.gonglue.activity;

import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.common.WindowController;
import lkt.gonglue.common.WindowManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.TextView;

public class DetailActivity extends Activity implements OnClickListener {
	private WebView mwv;
	private TextView mTvTitle;
	private TextView mTvComment;
	private WindowController mWindowController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		mWindowController = WindowManager.getWindow(this, WindowManager.WINDOW_TYPE_COMMENT);
		findViews();
		setDatas();
	}

	private void findViews() {
		mwv = (WebView) findViewById(R.id.wv);
		mTvTitle = (TextView) findViewById(R.id.tv_layout_actionbar);
		mTvComment = (TextView) findViewById(R.id.tv_layout_actionbar_comment);
		mTvComment.setOnClickListener(this);
	}

	private void setDatas() {
		Intent intent = getIntent();
		int type = intent.getIntExtra("type", 0);
		int page = intent.getIntExtra("page", 0);
		if (type == 0) {
			mTvTitle.setText("攻略");
			mwv.loadUrl("file:///android_asset/lm" + page % 2 + ".html");
		} else {
			mTvTitle.setText("技巧");
			mwv.loadUrl("file:///android_asset/lm" + page % 2 + ".html");
		}
	}

	@Override
	public void onClick(View v) {
		mWindowController.show();
	}

}
