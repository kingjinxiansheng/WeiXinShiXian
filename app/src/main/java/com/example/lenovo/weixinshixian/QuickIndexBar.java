package com.example.lenovo.weixinshixian;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class QuickIndexBar extends View {
	private Paint mPaint;
	private int mWidth;
	private String[] mIndexArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
			"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
			"W", "X", "Y", "Z" };
	private float mCellHeight;

	// ��������new�����ʱ�����
	public QuickIndexBar(Context context) {
		this(context, null);
	}

	// ��xml���������ÿؼ���ʱ����� (attrs����)
	public QuickIndexBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	// ��xml���������ÿؼ�����������ʽ��ʱ�����
	public QuickIndexBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	// ����onMessure֮�����
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mWidth = getMeasuredWidth();
		mCellHeight = getMeasuredHeight() * 1.0F / mIndexArr.length;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	private void init() {
		mPaint = new Paint();
		// ���ÿ����
		mPaint.setAntiAlias(true);
		// ���û��ʻ��Ƶ���ɫ�ǰ�ɫ
		mPaint.setColor(Color.WHITE);
		// ���û��ʻ��Ƶ������СΪ16sp
		mPaint.setTextSize(16);

		// ���û��ʻ��Ƶ��ı�����ʼ��Ϊ�ı����±�Ե(�ı�y��)�����ĵ�
		mPaint.setTextAlign(Align.CENTER);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mIndexArr.length; i++) {
			float x = mWidth / 2;
			float y = mCellHeight / 2 + getTextHeight(mIndexArr[i]) / 2 + i
					* mCellHeight;

			mPaint.setColor(i == mLastIndex ? Color.BLUE : Color.WHITE);
			canvas.drawText(mIndexArr[i], x, y, mPaint);
		}
		super.onDraw(canvas);
	}

	// �õ�View�����ֵĸ߶�
	private int getTextHeight(String text) {
		Rect bounds = new Rect();
		mPaint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}

	private int mLastIndex = -1;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			int index = (int) (event.getY() / mCellHeight);

			if (index != mLastIndex) { // ������ĸ��һ����ʱ����˾
				if (index >= 0 && index < mIndexArr.length) {

					if (mLetterListenner != null) {

						mLetterListenner.onTouchLetter(mIndexArr[index]);
					}
				}

			}
			invalidate();// onDraw()�����ᱻ���µ���
			mLastIndex = index;
			break;

		case MotionEvent.ACTION_UP:
			mLastIndex = -1;

			break;

		}
		return true;
	}

	// ͨ���ӿڻص��ķ�ʽ������ṩ��ȥ
	public interface onTouchLetterListenner {
		public abstract void onTouchLetter(String letter);
	}

	private onTouchLetterListenner mLetterListenner;

	public void setOnTouchLetterListenner(onTouchLetterListenner listenner) {
		this.mLetterListenner = listenner;
	}

}
