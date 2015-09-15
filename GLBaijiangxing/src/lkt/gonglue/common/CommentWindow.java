package lkt.gonglue.common;

import java.util.List;

import lkt.gonglue.baijiangxing.R;
import lkt.gonglue.db.CommentDao;
import lkt.gonglue.entity.Comment;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CommentWindow extends BaseWindow implements OnClickListener {
	private ListView mLvComment;
	private EditText mEtText;
	private Button mBtnSend;
	private CommentAdapter mCommentAdapter;
	private List<Comment> mDatas ;

	public CommentWindow(Activity mAct) {
		super(mAct, R.layout.layout_pop_comment);
		findViews();
		setDatas();
	}

	void findViews() {
		if (mView != null) {
			mLvComment = (ListView) mView
					.findViewById(R.id.lv_pop_comment_content);
			mEtText = (EditText) mView.findViewById(R.id.et_edit_send_text);
			mBtnSend = (Button) mView.findViewById(R.id.btn_edit_send_send);
			mBtnSend.setOnClickListener(this);
		}
	}

	private void setDatas() {
		mDatas = CommentDao.query();
		mCommentAdapter = new CommentAdapter(mAct, mDatas);
		mLvComment.setAdapter(mCommentAdapter);
	}

	@Override
	public void onClick(View v) {
		String text = mEtText.getText().toString();
		if (text != null && text.trim().length() > 0) {
			Comment com = new Comment(null, text);
			if (CommentDao.insert(com) > 0) {
				mDatas.add(com);
				mEtText.getText().clear();
				mCommentAdapter.notifyDataSetChanged();
				mLvComment.setSelection(mDatas.size() - 1);
			} else {
				Toast.makeText(mAct, "评论失败，请检查网络", Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(mAct, "请勿输入空评论", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	void afterShowing() {
		mLvComment.setSelection(mDatas.size() - 1);
	}

}
