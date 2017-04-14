package com.envee.eshopping;

import com.envee.eshopping.econtrol.SearchView;

import com.envee.eshopping.econtrol.SearchView.OnServiewClickListener;
import android.app.Activity;
import android.os.Bundle;

public class SearchActivity extends Activity {

	
	private SearchView searchView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_activity);
		initView();
	}
	
	private void initView (){
		searchView = (SearchView)findViewById(R.id.searchview_layout);
		searchView.setOnServiewClickListener(onServiewClickListener);
	}


	private OnServiewClickListener onServiewClickListener = new OnServiewClickListener(){

		@Override
		public void onSearchclicke(String string) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onBackclicke() {
			// TODO Auto-generated method stub
			finish();
			
		}
		
	};

}
