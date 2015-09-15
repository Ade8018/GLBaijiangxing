package lkt.gonglue.adapter;

import java.util.List;
import java.util.Random;

import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.entity.Lv;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LvAdapter extends BaseAdapter {
	public int[] idss = { R.drawable.lm0, R.drawable.lm1, R.drawable.lm2, R.drawable.lm3, R.drawable.lm4, R.drawable.lm5, R.drawable.lm6, R.drawable.lm7, R.drawable.lm8, R.drawable.lm9 };
	private List<Lv> mDatas;
	private Context mContext;
	public int[] ids = new int[idss.length];

	public LvAdapter(List<Lv> mDatas, Context mContext) {
		this.mDatas = mDatas;
		this.mContext = mContext;
		for (int i = 0; i < ids.length; i++) {
			ids[i] = idss[new Random().nextInt(ids.length)];
		}
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		ImageView iv;
		TextView tvTitle;
		TextView tvContent;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_lv_frg_mid, parent, false);
			vh = new ViewHolder();
			vh.iv = (ImageView) convertView.findViewById(R.id.iv_item_lv_frg_mid);
			vh.tvTitle = (TextView) convertView.findViewById(R.id.tv_item_lv_frg_mid_title);
			vh.tvContent = (TextView) convertView.findViewById(R.id.tv_item_lv_frg_mid_content);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		vh.iv.setImageDrawable(mContext.getResources().getDrawable(ids[position]));
		vh.tvTitle.setText(mDatas.get(position).title);
		vh.tvContent.setText(mDatas.get(position).content);
		return convertView;
	}

}
