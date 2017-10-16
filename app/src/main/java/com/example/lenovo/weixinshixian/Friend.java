package com.example.lenovo.weixinshixian;

public class Friend implements Comparable<Friend> {
	private String mName;
	private String mPinYin;

	public String getmPinYin() {
		return mPinYin;
	}

	public void setmPinYin(String mPinYin) {
		this.mPinYin = mPinYin;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public Friend(String mName) {
		super();
		this.mName = mName;
		setmPinYin(PinYinUtils.getPinYin(mName));

	}

	@Override
	public int compareTo(Friend another) {
		return getmPinYin().compareTo(another.getmPinYin());
	}

}
