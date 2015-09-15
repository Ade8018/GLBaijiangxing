package lkt.gonglue.fragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lkt.gonglue.activity.DetailActivity;
import lkt.gonglue.adapter.LvAdapter;
import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.entity.Lv;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class MidFrg extends Fragment {
	private View mView;
	private TextView mTvTitle;
	private PullToRefreshListView mLv;
	private List<Lv> mDatas;
	private LvAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (mView == null) {
			mView = inflater.inflate(R.layout.fragment_mid, container, false);
			findViews();
		}
		setDatas();
		return mView;
	}

	private void findViews() {
		mTvTitle = (TextView) mView.findViewById(R.id.tv_layout_actionbar);
		mLv = (PullToRefreshListView) mView.findViewById(R.id.lv_frg_mid);
		mLv.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel("更新完成");
				mLv.postDelayed(new Runnable() {
					@Override
					public void run() {
						mLv.onRefreshComplete();
					}
				}, new Random().nextInt(3)*1000+500);
			}
		});
		mLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), DetailActivity.class);
				intent.putExtra("type", 0);
				intent.putExtra("page", position);
				startActivity(intent);
			}
		});
	}

	private void setDatas() {
		mTvTitle.setText("攻略");
		mDatas = new ArrayList<Lv>();
		getText();
		mAdapter = new LvAdapter(mDatas, getActivity());
		mLv.setAdapter(mAdapter);
	}

	private void getText() {
		InputStream is = null;
		BufferedReader br = null;
		try {
			is = getActivity().getAssets().open("lm.txt");
			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			int i = 0;
			Lv l = null;
			while ((line = br.readLine()) != null) {
				if (i % 2 == 0) {
					l = new Lv();
					l.title = line;
				} else {
					l.content = line;
					mDatas.add(l);
				}
				i++;
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
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mView != null) {
			((ViewGroup) mView.getParent()).removeView(mView);
		}
	}
}
