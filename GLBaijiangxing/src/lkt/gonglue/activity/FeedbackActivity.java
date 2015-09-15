package lkt.gonglue.activity;

import java.util.Random;

import lkt.gonglue.baijiangxing.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends Activity implements OnClickListener {
	private TextView mTvTitle;
	private EditText mEt;
	private Button mBtn;
	private ProgressDialog mDlg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		findViews();
	}

	private void findViews() {
		mTvTitle = (TextView) findViewById(R.id.tv_layout_actionbar);
		mTvTitle.setText("意见及反馈");
		mEt = (EditText) findViewById(R.id.et_feedback);
		mBtn = (Button) findViewById(R.id.btn_feedback);
		mBtn.setOnClickListener(this);

		mDlg = new ProgressDialog(this);
		mDlg.setMessage("提交中，请稍后...");
		mDlg.setCancelable(false);
		mDlg.setCanceledOnTouchOutside(false);
		mDlg.setOnCancelListener(new OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				mEt.getText().clear();
				Toast.makeText(FeedbackActivity.this, "您的意见已提交。感谢您的细心建议，我们会加倍努力！", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		String text = mEt.getText().toString();
		if (text.trim().length() > 0) {
			if (mDlg != null && !mDlg.isShowing()) {
				mDlg.show();
				mEt.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (mDlg != null && mDlg.isShowing()) {
							mDlg.cancel();
						}
					}
				}, new Random().nextInt(3) * 1000 + 500);
			}
		} else {
			Toast.makeText(this, "请勿输入空内容！", Toast.LENGTH_SHORT).show();
		}
	}

}
