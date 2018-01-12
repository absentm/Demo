package com.dm.materialdrawerdemo.utils;

import android.content.Context;
import android.content.res.TypedArray;

import com.dm.materialdrawerdemo.R;

/**
 * Utils
 * Created by dm on 16-12-1.
 */
public class Utils {
    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static int getTabsHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.tabsHeight);
    }
}
