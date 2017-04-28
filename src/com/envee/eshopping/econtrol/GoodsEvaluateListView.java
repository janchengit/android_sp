package com.envee.eshopping.econtrol;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class GoodsEvaluateListView extends ListView {
    public GoodsEvaluateListView(Context context) {
        super(context);
    }

    public GoodsEvaluateListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GoodsEvaluateListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // Adapt ScrollView
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
