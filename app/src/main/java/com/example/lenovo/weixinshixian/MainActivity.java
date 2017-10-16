package com.example.lenovo.weixinshixian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewAnimator;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

//自定义控件(自定义组合控件,完全自定义控件(继承View,继承ViewGroup,继承Android中已有控件))
public class MainActivity extends Activity {
    private QuickIndexBar mBar;
    private ListView mLv;
    private List<Friend> mList = new ArrayList<Friend>();
    private TextView mtv_center_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
        mLv = (ListView) findViewById(R.id.lv);
        mtv_center_view = (TextView) findViewById(R.id.tv_center_view);

        fillList();
        Collections.sort(mList);
        MyListViewAdapter adapter = new MyListViewAdapter(MainActivity.this,
                mList);
        mLv.setAdapter(adapter);

        mBar.setOnTouchLetterListenner(new QuickIndexBar.onTouchLetterListenner() {

            @Override
            public void onTouchLetter(String letter) {

                for (int i = 0; i < mList.size(); i++) {

                    String lvLetter = mList.get(i).getmPinYin().charAt(0) + "";

                    if (lvLetter.equals(letter)) {

                        mLv.setSelection(i);
                        // 为了防止一直去遍历相同字母对应的条目
                        break;
                    }

                }

                showTouchCenterView(letter);
            }
        });

        // 一开始以缩小到0的形式隐藏
        ViewHelper.setScaleX(mtv_center_view, 0);
        ViewHelper.setScaleY(mtv_center_view, 0);
    }

    private Handler mHandler = new Handler();

    private boolean mIsScale;

    // 显示中间的View 补间动画(View动画)(透明度，缩放，旋转，平移，综合动画)
    // 帧动画(顺序播放排列好的图片类似电影),属性动画(透明度，缩放，旋转，平移，综合动画)
    protected void showTouchCenterView(String letter) {
        if (!mIsScale) {
            mIsScale = true;
            // 放大到原先的大小
            ViewPropertyAnimator.animate(mtv_center_view).scaleX(1)
                    .setInterpolator(new OvershootInterpolator()).setDuration(400).start();
            ViewPropertyAnimator.animate(mtv_center_view).scaleY(1)
                    .setInterpolator(new OvershootInterpolator()).setDuration(400).start();
        }
        mtv_center_view.setText(letter);
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewPropertyAnimator.animate(mtv_center_view).scaleX(0)
                        .setInterpolator(new OvershootInterpolator()).setDuration(400).start();
                ViewPropertyAnimator.animate(mtv_center_view).scaleY(0)
                        .setInterpolator(new OvershootInterpolator()).setDuration(400).start();
                mIsScale=false;
            }
        }, 1500);
    }

    // 添加数据
    private void fillList() {
        mList.add(new Friend("杨晨"));
        mList.add(new Friend("魏正洋"));
        mList.add(new Friend("姜贤凯"));
        mList.add(new Friend("张硕"));
        mList.add(new Friend("张三"));
        mList.add(new Friend("黑明阳"));
        mList.add(new Friend("魏珂珂"));
        mList.add(new Friend("侯洪刚"));
        mList.add(new Friend("史文胜"));
        mList.add(new Friend("石再全"));
        mList.add(new Friend("王龙"));
        mList.add(new Friend("马玉新"));
        mList.add(new Friend("马志武"));
        mList.add(new Friend("马云"));
        mList.add(new Friend("晋玉芬"));
        mList.add(new Friend("因天鹏"));
        mList.add(new Friend("班主任"));
        mList.add(new Friend("冯玉苗"));
        mList.add(new Friend("李旭斌"));
        mList.add(new Friend("阿三"));
        mList.add(new Friend("陆向阳"));
        mList.add(new Friend("阿四"));

    }

}

