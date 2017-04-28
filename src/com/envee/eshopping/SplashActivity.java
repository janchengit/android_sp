package com.envee.eshopping;

import com.envee.eshopping.data.ObbManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	private static final String TAG = "SplashActivity";

	private static final boolean LOG_ENABLE = true;
	
	private static final int DELAT_START_TIME = 1500;
	private static final int DELAT_SPLASH_TIME = 500;

	private static final int MESSAGE_BASE = 100;
	private static final int MESSAGE_START = MESSAGE_BASE + 100;
	private static final int MESSAGE_SPLASH = MESSAGE_BASE + 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_activity_layout);
		ObbManager.getInstance(this);
		mHandler.sendEmptyMessageDelayed(MESSAGE_START, DELAT_START_TIME);
	}

	private void JumpToMainActivity() {
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, Eshopping.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		startActivity(intent);
		finish();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(LOG_ENABLE){
			Log.e(TAG, "onStart ENTER ");
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(LOG_ENABLE){
			Log.e(TAG, "onResume ENTER ");
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(LOG_ENABLE){
			Log.e(TAG, "onPause ENTER ");
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if(LOG_ENABLE){
			Log.e(TAG, "onStop ENTER ");
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(LOG_ENABLE){
			Log.e(TAG, "onDestroy ENTER ");
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_START:
				mHandler.sendEmptyMessageDelayed(MESSAGE_SPLASH, DELAT_SPLASH_TIME);
				break;
			case MESSAGE_SPLASH:
				if (!ObbManager.getInstance(null).isObbMounted()) {
					showDialog();
				} else {
					JumpToMainActivity();
				}
				break;
			}
			super.handleMessage(msg);
		}
	};

	private void showDialog() {
		Log.e(TAG, "Mount obb failed");
	}

}
