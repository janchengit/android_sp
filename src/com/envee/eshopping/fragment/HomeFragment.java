package com.envee.eshopping.fragment;

import com.envee.eshopping.R;
import com.envee.eshopping.SearchActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeFragment extends Fragment {


	// Search button
	private ImageView btnSearchActivity = null;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.homepage_frag, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		// 1. Search button 
		btnSearchActivity=(ImageView) view.findViewById(R.id.btn_search_activity);
		btnSearchActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Jump to search page
				Intent intent=new Intent(getActivity(),SearchActivity.class);
				startActivity(intent);
			}
		});
	}
	
}

