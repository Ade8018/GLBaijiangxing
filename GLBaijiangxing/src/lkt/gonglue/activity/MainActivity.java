package lkt.gonglue.activity;

import java.util.ArrayList;
import java.util.List;

import lkt.gonglue.adapter.VpAdapter;
import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.db.CommentDao;
import lkt.gonglue.fragment.LeftFrg;
import lkt.gonglue.fragment.MidFrg;
import lkt.gonglue.fragment.RightFrg;
import lkt.gonglue.fragment.SettingFrg;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;

public class MainActivity extends FragmentActivity {
	private ViewPager mVp;
	private VpAdapter mAdapter;
	private List<Fragment> mFrgs;
	private RadioButton[] mRbs = new RadioButton[4];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		CommentDao.init(getApplicationContext());
		findViews();
		setDatas();
	}

	private void findViews() {
		mVp = (ViewPager) findViewById(R.id.vp_main);
		mVp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				mRbs[arg0].setChecked(true);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		mRbs[0] = (RadioButton) findViewById(R.id.rb_layout_navi_left);
		mRbs[1] = (RadioButton) findViewById(R.id.rb_layout_navi_mid);
		mRbs[2] = (RadioButton) findViewById(R.id.rb_layout_navi_right);
		mRbs[3] = (RadioButton) findViewById(R.id.rb_layout_navi_setting);
		for (int i = 0; i < mRbs.length; i++) {
			mRbs[i].setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						switch (buttonView.getId()) {
						case R.id.rb_layout_navi_left:
							mVp.setCurrentItem(0);
							break;
						case R.id.rb_layout_navi_mid:
							mVp.setCurrentItem(1);
							break;
						case R.id.rb_layout_navi_right:
							mVp.setCurrentItem(2);
							break;
						case R.id.rb_layout_navi_setting:
							mVp.setCurrentItem(3);
							break;
						default:
							break;
						}
					}
				}
			});
		}
	}

	private void setDatas() {
		mFrgs = new ArrayList<Fragment>();
		mFrgs.add(new LeftFrg());
		mFrgs.add(new MidFrg());
		mFrgs.add(new RightFrg());
		mFrgs.add(new SettingFrg());
		mAdapter = new VpAdapter(getSupportFragmentManager(), mFrgs);
		mVp.setAdapter(mAdapter);
		mRbs[0].setChecked(true);
	}
}
