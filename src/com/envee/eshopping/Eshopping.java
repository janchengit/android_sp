package com.envee.eshopping;

import com.envee.eshopping.data.GoodsData;
import com.envee.eshopping.data.ObbManager;
import com.envee.eshopping.fragment.HomeFragment;
import com.envee.eshopping.fragment.MyaccountFragment;
import com.envee.eshopping.fragment.ShoppingcartFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class Eshopping extends FragmentActivity implements OnClickListener {

	// Initialize some constant variables
	private static String TAG = "eshopping";
	
	// 1. The bottom btns
	private View[] arrBotBtnView = new View[3];

	private int[] arrBotBtnId = { R.id.bt_homepage, R.id.bt_shoppingcart,
			R.id.bt_myaccount };
	
	private ImageView[] arrBotImgView = new ImageView[3];
	
	private int[] arrBotImgId = { R.id.img_homepage, R.id.img_shoppingcart,
			R.id.img_myaccount };

	private int[] arrBotBtnFcImg = { R.drawable.img_homepage_fc,
			R.drawable.img_shoppingcart_fc, R.drawable.img_myaccount_fc };

	private int[] arrBotBtnNmImg = { R.drawable.img_homepage_nm,
			R.drawable.img_shoppingcart_nm, R.drawable.img_myaccount_nm };

	private int nSelectedBtnId = -1;

	// 2. TODO: The fragment pages (home page/shopping cart/my account)
	private HomeFragment fragHomepage = null;
	private ShoppingcartFragment fragShoppingCart = null;
	private MyaccountFragment fragMyaccount = null;
	
	// 3. Files relative variable

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.eshopping_activity_horizontal);
		initData();
		initView();
		selectBottomBtnById(arrBotBtnId[0]);
	}

	private void initData() {
		// TODO: Initialize saved data
		GoodsData goodsdata = GoodsData.getInstance(this);
		// Read json and fill the goods data db
	}

	private void initView() {
		// 1. Set listener of the button clicks
		for (int i = 0; i < arrBotBtnView.length; i++) {
			arrBotImgView[i] = (ImageView) findViewById(arrBotImgId[i]);
			
			arrBotBtnView[i] = (View) findViewById(arrBotBtnId[i]);
			arrBotBtnView[i].setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		selectBottomBtnById(v.getId());
	}

	private void selectBottomBtnById(int id) {
		for (int i = 0; i < arrBotBtnId.length; i++) {
			if (id == arrBotBtnId[i]) {
				arrBotImgView[i].setImageResource(arrBotBtnFcImg[i]);
				showFragmentSelected(arrBotBtnId[i]);
				continue;
			}
			arrBotImgView[i].setImageResource(arrBotBtnNmImg[i]);
		}
	}

	private Fragment getFragmentFromBtnId(int id) {
		if (id == R.id.bt_homepage) {
			if (fragHomepage == null) {
				fragHomepage = new HomeFragment();
				addFragment(fragHomepage);
			}
			return fragHomepage;
		} else if (id == R.id.bt_shoppingcart) {
			if (fragShoppingCart == null) {
				fragShoppingCart = new ShoppingcartFragment();
				addFragment(fragShoppingCart);
			}
			return fragShoppingCart;
		} else if (id == R.id.bt_myaccount) {
			if (fragMyaccount == null) {
				fragMyaccount = new MyaccountFragment();
				addFragment(fragMyaccount);
			}
			return fragMyaccount;
		}
		return null;
	}

	public void showFragmentSelected(int id) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		if (nSelectedBtnId == id) {
			Log.v("E1", "The same ID selected: " + id);
			return;
		}
		if (getFragmentFromBtnId(nSelectedBtnId) != null) {
			ft.hide(getFragmentFromBtnId(nSelectedBtnId));
		}
		ft.show(getFragmentFromBtnId(id));
		ft.commitAllowingStateLoss();
		nSelectedBtnId = id;
	}

	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.main_content, fragment);
		ft.commit();
	}

	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

}
