package com.envee.eshopping.other;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class EViewPage extends ViewPager {
	public EViewPage(Context context) {
		super(context);
	}

	public EViewPage(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		try {
			
			return super.onInterceptTouchEvent(ev);
		}  catch (IllegalArgumentException e) {

			return false;
		}catch(ArrayIndexOutOfBoundsException e ){

			return false;
		}
	}
}
