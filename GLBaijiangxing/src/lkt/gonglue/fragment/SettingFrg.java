package lkt.gonglue.fragment;

import lkt.gonglue.activity.FeedbackActivity;
import lkt.gonglue.baijiangxing.R;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingFrg extends Fragment implements OnClickListener {
	private View mView;
	private TextView mTvTitle;
	private RelativeLayout mRlPersonalInfo;
	private RelativeLayout mRlCollection;
	private RelativeLayout mRlClear;
	private RelativeLayout mRlVersion;
	private RelativeLayout mRlFeedback;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_setting, container, false);
			findViews();
		}
		setDatas();
		return mView;
	}

	private void findViews() {
		mTvTitle = (TextView) mView.findViewById(R.id.tv_layout_actionbar);
		mRlPersonalInfo = (RelativeLayout) mView.findViewById(R.id.rl_setting_personal_info);
		mRlCollection = (RelativeLayout) mView.findViewById(R.id.rl_setting_collection);
		mRlClear = (RelativeLayout) mView.findViewById(R.id.rl_setting_clear_cache);
		mRlVersion = (RelativeLayout) mView.findViewById(R.id.rl_setting_version_info);
		mRlFeedback = (RelativeLayout) mView.findViewById(R.id.rl_setting_feedback);
		
		mRlCollection.setVisibility(View.GONE);

		mRlClear.setOnClickListener(this);
		mRlCollection.setOnClickListener(this);
		mRlPersonalInfo.setOnClickListener(this);
		mRlVersion.setOnClickListener(this);
		mRlFeedback.setOnClickListener(this);

		mPdlg = new ProgressDialog(getActivity());
		mPdlg.setCancelable(false);
		mPdlg.setCanceledOnTouchOutside(false);
		mAdlg = new AlertDialog.Builder(getActivity()).create();
		mAdlgVersion = new AlertDialog.Builder(getActivity()).create();

	}

	private void setDatas() {
		mTvTitle.setText("设置");
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mView != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_setting_clear_cache:
			onClearCache();
			break;
		case R.id.rl_setting_collection:
			break;
		case R.id.rl_setting_feedback:
			onFeedback();
			break;
		case R.id.rl_setting_personal_info:
			break;
		case R.id.rl_setting_version_info:
			onVersion();
			break;

		default:
			break;
		}
	}

	private void onVersion() {
		mAdlgVersion.setTitle("版本信息");
		mAdlgVersion.setMessage("当前版本：1.0");
		mAdlgVersion.setButton(AlertDialog.BUTTON_POSITIVE, "马上更新", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				mPdlg.setMessage("查询版本信息中，请稍后...");
				if (mAdlgVersion != null && mAdlgVersion.isShowing()) {
					mAdlgVersion.dismiss();
					mPdlg.show();
					mView.postDelayed(new Runnable() {
						@Override
						public void run() {
							mPdlg.dismiss();
							Toast.makeText(getActivity(), "当前版本已是最新版本！", Toast.LENGTH_SHORT).show();
						}
					}, 1000);
				}
			}
		});
		mAdlgVersion.show();
	}

	AlertDialog mAdlg;
	AlertDialog mAdlgVersion;
	ProgressDialog mPdlg;

	private void onClearCache() {
		mPdlg.setMessage("清除缓存中...");

		mAdlg.setTitle("清空缓存");
		mAdlg.setMessage("是否清空缓存？");
		mAdlg.setButton(AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (mAdlg != null && mAdlg.isShowing()) {
					mAdlg.dismiss();
					mPdlg.show();
					mView.postDelayed(new Runnable() {
						@Override
						public void run() {
							mPdlg.dismiss();
							Toast.makeText(getActivity(), "缓存已清除！", Toast.LENGTH_SHORT).show();
						}
					}, 1000);
				}
			}
		});
		mAdlg.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (mAdlg != null && mAdlg.isShowing()) {
					mAdlg.dismiss();
				}
			}
		});
		mAdlg.show();
	}

	private void onFeedback() {
		startActivity(new Intent(getActivity(), FeedbackActivity.class));
	}

}
