package com.example.myapplication;

import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    PopupWindow mPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.mToolbar);
        //主标题
        setTitle("LianXi");
        //设置副标题
//        mToolbar.setSubtitle("demo");
        //设置logo
//        mToolbar.setLogo(R.mipmap.verygoodselect);

        setSupportActionBar(mToolbar);

        //添加绘图按钮的图片
        mToolbar.setNavigationIcon(R.mipmap.check_me);
        //设置回退的点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                popUpMyOverflow();
                return true;
            }
        });
    }

    /**
     * 弹出自定义的popWindow
     */
    private void popUpMyOverflow() {
        //获得状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + mToolbar.getHeight();
        if (mPopup == null) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.popup, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopup = new PopupWindow(popView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopup.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopup.setOutsideTouchable(true);
            //设置一个动画。
            mPopup.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopup.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        } else {
            mPopup.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
