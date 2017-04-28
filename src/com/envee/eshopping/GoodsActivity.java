package com.envee.eshopping;

import java.util.ArrayList;
import java.util.List;

import com.envee.eshopping.adapter.GoodsdetailListViewAdapter;
import com.envee.eshopping.data.GoodsData;
import com.envee.eshopping.goods.Goods;
import com.envee.eshopping.other.AsyncImageLoader;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsActivity extends FragmentActivity implements
		OnItemClickListener, OnClickListener {

	NfcAdapter nfcAdapter;

	private ViewPager viewPager;
	
	private ArrayList<View> allListView;
	
	private AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
	
	private ListView listView;
	
	private ImageView iv_baby_collection;
	
	/** 弹出商品订单信息详情 */
	//private BabyPopWindow popWindow;
	/** 用于设置背景暗淡 */
	private LinearLayout all_choice_layout = null;
	/** 判断是否点击的立即购买按钮 */
	boolean isClickBuy = false;
	/** 是否添加收藏 */
	private static boolean isCollection = false;

	private Goods goods = null;
	
	private List<String> goodsImgFileList = null;
	
	private TextView tvPrice = null;

	private TextView tvHaveSaled = null;

	private TextView tvLeaveCounts = null;

	private TextView tvName = null;

	private TextView tvCreateTime = null;

	private TextView tvDescribe = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.goods_activity);
		Intent intent = getIntent();
		initGoodsAbout(intent.getIntExtra("goodsid", 0));
		getSaveCollection();
		initView();
		//popWindow = new BabyPopWindow(this);
		//popWindow.setOnItemClickListener(this);
	}
	
	private void initGoodsAbout(int id) {
		goods = GoodsData.getInstance(null).getGoodsById(id);
		goodsImgFileList = goods.getGoodsImgList();
	}

	@SuppressLint("NewApi")
	private void initView() {

		((ImageView) findViewById(R.id.iv_back)).setOnClickListener(this);
		((ImageView) findViewById(R.id.put_in)).setOnClickListener(this);
		((ImageView) findViewById(R.id.buy_now)).setOnClickListener(this);
		
		tvPrice = (TextView) findViewById(R.id.tv_price);
		tvPrice.setText(goods.getPrice() + "");
		
		tvHaveSaled = (TextView) findViewById(R.id.tv_sales);
		tvHaveSaled.setText(goods.getSales() + "");
		
		tvLeaveCounts = (TextView) findViewById(R.id.tv_counts);
		tvLeaveCounts.setText(goods.getCounts() + "");
		
		tvName = (TextView) findViewById(R.id.tv_goods_name);
		tvName.setText(goods.getName());
		
		tvDescribe = (TextView) findViewById(R.id.tv_goods_description);
		tvDescribe.setText(goods.getDescribe());
		
		tvCreateTime = (TextView) findViewById(R.id.tv_createtime);
		tvCreateTime.setText(goods.getCreatetime());
		
		iv_baby_collection = (ImageView) findViewById(R.id.iv_goods_collection);
		iv_baby_collection.setOnClickListener(this);
		all_choice_layout = (LinearLayout) findViewById(R.id.all_choice_layout);
		listView = (ListView) findViewById(R.id.listView_Detail);
		listView.setFocusable(false);
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));
		listView.setAdapter(new GoodsdetailListViewAdapter(this));
		listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
//				Uri uri = Uri.parse("http://yecaoly.taobao.com");
//				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(intent);
			}
		});
		initViewPager();

		if (isCollection) {
			// 如果已经收藏，则显示收藏后的效果
			iv_baby_collection.setImageResource(R.drawable.collection_over);
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.iv_back:
			// 返回
			finish();
			break;
		case R.id.iv_goods_collection:
			// 收藏
			if (isCollection) {
				// 提示是否取消收藏
				cancelCollection();
			} else {
				isCollection = true;
				setSaveCollection();
				// 如果已经收藏，则显示收藏后的效果
				iv_baby_collection
						.setImageResource(R.drawable.collection_over);
				Toast.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.put_in:
			// 添加购物车
			isClickBuy = false;
			setBackgroundBlack(all_choice_layout, 0);
//			popWindow.showAsDropDown(view);
			break;
		case R.id.buy_now:
			// 立即购买
			isClickBuy = true;
			setBackgroundBlack(all_choice_layout, 0);
//			popWindow.showAsDropDown(view);
			break;
		}
	}

	private void initViewPager() {

		if (allListView != null) {
			allListView.clear();
			allListView = null;
		}
		allListView = new ArrayList<View>();

		for (int i = 0; i < goodsImgFileList.size(); i++) {
			
			View view = LayoutInflater.from(this).inflate(R.layout.pic_item, null);
			ImageView imageView = (ImageView) view.findViewById(R.id.pic_item);
			
			loadImage(goodsImgFileList.get(i), imageView);
			imageView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// Goto big view / scale it
//					Intent intent = new Intent(GoodsActivity.this, ShowBigPictrue.class);
//					intent.putExtra("position", position);
//					startActivity(intent);
				}
			});
			allListView.add(view);
		}

		viewPager = (ViewPager) findViewById(R.id.iv_goodsviewpage);
		ViewPagerAdapter adapter = new ViewPagerAdapter();
		viewPager.setAdapter(adapter);

	}

	private class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return allListView.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (View) arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = allListView.get(position);
			container.addView(view);
			return view;
		}

	}

    private void loadImage(final String url, final ImageView imageView)
    {
        Drawable cacheImage = asyncImageLoader.loadDrawable(url, new AsyncImageLoader.ImageCallback()
        {
            public void imageLoaded(Drawable imageDrawable)
            {
            	imageView.setImageDrawable(imageDrawable);
            }
        });
        if (cacheImage != null)
        {
        	imageView.setImageDrawable(cacheImage);
        }
    }
	
	// 点击弹窗的确认按钮的响应
//	@Override
//	public void onClickOKPop() {
//		setBackgroundBlack(all_choice_layout, 1);
//
//		if (isClickBuy) {
//			// 如果之前是点击的立即购买，那么就跳转到立即购物界面
//			Intent intent = new Intent(BabyActivity.this, BuynowActivity.class);
//			startActivity(intent);
//		} else {
//			Toast.makeText(this, "添加到购物车成功", Toast.LENGTH_SHORT).show();
//		}
//	}

	/** 控制背景变暗 0变暗 1变亮 */
	public void setBackgroundBlack(View view, int what) {
		switch (what) {
		case 0:
			view.setVisibility(View.VISIBLE);
			break;
		case 1:
			view.setVisibility(View.GONE);
			break;
		}
	}

	/** 保存是否添加收藏 */
	private void setSaveCollection() {
		SharedPreferences sp = getSharedPreferences("SAVECOLLECTION",
				Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putBoolean("isCollection", isCollection);
		editor.commit();
	}

	private void getSaveCollection() {
		SharedPreferences sp = getSharedPreferences("SAVECOLLECTION",
				Context.MODE_PRIVATE);
		isCollection = sp.getBoolean("isCollection", false);

	}

	private void cancelCollection() {
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("是否取消收藏");
		dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				isCollection = false;
				iv_baby_collection.setImageResource(R.drawable.collection_normal);
				setSaveCollection();
			}
		});
		dialog.setNegativeButton("取消", null);
		dialog.create().show();

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
