package lkt.gonglue.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VpAdapter extends FragmentPagerAdapter {
	private List<Fragment> mFrgs;

	public VpAdapter(FragmentManager fm, List<Fragment> mFrgs) {
		super(fm);
		this.mFrgs = mFrgs;
	}

	@Override
	public Fragment getItem(int arg0) {
		return mFrgs.get(arg0);
	}

	@Override
	public int getCount() {
		return mFrgs.size();
	}

}
