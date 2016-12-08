package com.dm.rxdemo.customviews.textStyle;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
import com.aspsine.swipetoloadlayout.SwipeTrigger;

/**
 * RefreshHeaderView
 * Created by dm on 16-12-8.
 */

public class RefreshHeaderView extends TextView
        implements SwipeRefreshTrigger, SwipeTrigger {

    public RefreshHeaderView(Context context) {
        super(context);
        this.setTextColor(Color.BLACK);  //此处定义颜色
    }

    public RefreshHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTextColor(Color.BLACK);  //此处定义颜色
    }

    @Override
    public void onRefresh() {
        setText("玩命加载中...");
    }

    @Override
    public void onPrepare() {
        setText("");
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                setText("有一种爱叫做放手...");
            } else {
                setText("");
            }
        } else {
            setText("");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
        setText("");
    }

    @Override
    public void onReset() {
        setText("");
    }
}
