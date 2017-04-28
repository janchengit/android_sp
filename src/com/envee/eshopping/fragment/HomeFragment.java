package com.envee.eshopping.fragment;

import com.envee.eshopping.GoodsActivity;
import com.envee.eshopping.R;
import com.envee.eshopping.SearchActivity;
import com.envee.eshopping.adapter.GoodsListViewAdapter;
import com.envee.eshopping.data.GoodsData;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class HomeFragment extends Fragment {

	// Search button
	private ImageView ivSearchActivity = null;

	private ListView lvListGoods = null;

	private GoodsListViewAdapter goodsLvAdapter = null;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.homepage_frag, null);
		initView(view);
		return view;
	}

	private void initView(View view) {
		// 1. Search button
		ivSearchActivity = (ImageView) view
				.findViewById(R.id.btn_search_activity);
		ivSearchActivity.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Jump to search page
				Intent intent = new Intent(getActivity(), SearchActivity.class);
				startActivity(intent);
			}
		});

		// 2. List goods
		lvListGoods = (ListView) view.findViewById(R.id.goods_list);
		goodsLvAdapter = new GoodsListViewAdapter(getActivity(), GoodsData
				.getInstance(null).getGoodsList());
		lvListGoods.setAdapter(goodsLvAdapter);
		lvListGoods.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				int goodsid = arg2;
				Intent intent = new Intent(getActivity(), GoodsActivity.class);
				intent.putExtra("goodsid", goodsid);
				startActivity(intent);
			}

		});
	}
}
