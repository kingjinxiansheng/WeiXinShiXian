package com.example.lenovo.weixinshixian;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Friend> mList;

	public MyListViewAdapter(Context context, List<Friend> list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.list_item, null);
		}

		ViewHolder holder = ViewHolder.getHolder(convertView);
		Friend friend = mList.get(position);

		holder.tv_name.setText(friend.getmName());

		String currentletter = friend.getmPinYin().charAt(0) + "";
		if (position > 0) {
			String lastLetter = mList.get(position - 1).getmPinYin().charAt(0)
					+ "";

			if (lastLetter.equals(currentletter)) {

				holder.tv_letter.setVisibility(View.GONE);
			} else {
				holder.tv_letter.setVisibility(View.VISIBLE);
				holder.tv_letter.setText(currentletter);
			}

		}else{
			holder.tv_letter.setVisibility(View.VISIBLE);
			holder.tv_letter.setText(currentletter);
		}
		return convertView;
	}

	// ���ؼ���findViewById setTag getTagȫ����װ��ViewHolder����
	static class ViewHolder {
		TextView tv_letter, tv_name;

		private ViewHolder(View convertView) {
			tv_letter = (TextView) convertView.findViewById(R.id.tv_letter);
			tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		}

		public static ViewHolder getHolder(View convertView) {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if (holder == null) {
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}

	}
}
