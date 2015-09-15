package lkt.gonglue.fragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import lkt.gonglue.baijiangxing.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class LeftFrg extends Fragment {
	private View mView;
	private TextView mTv;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_left, container, false);
			findViews();
		}
		return mView;
	}

	private void findViews() {
		mTv = (TextView) mView.findViewById(R.id.tv_frg_left_content);
		String text = "";
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = getActivity().getAssets().open("introduce.txt");
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				text += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		mTv.setText(text);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mView != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
	}
}
