package lkt.gonglue.common;

import java.util.List;

import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.entity.Comment;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentAdapter extends BaseAdapter {
	private Context mContext;
	private List<Comment> mDatas;

	public CommentAdapter(Context mContext, List<Comment> mDatas) {
		this.mContext = mContext;
		this.mDatas = mDatas;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mDatas.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		TextView tvName;
		TextView tvText;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_comment, parent, false);
			vh = new ViewHolder();
			vh.tvName = (TextView) convertView
					.findViewById(R.id.tv_item_comment_name);
			vh.tvText = (TextView) convertView
					.findViewById(R.id.tv_item_comment_text);
			vh.tvName.getPaint().setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
//		vh.tvName.setText(mDatas.get(position).name);
		vh.tvText.setText(mDatas.get(position).text);
		return convertView;
	}

}
