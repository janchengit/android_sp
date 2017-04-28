package com.envee.eshopping.adapter;

import java.util.ArrayList;
import com.envee.eshopping.R;
import com.envee.eshopping.fragment.HomeFragment;
import com.envee.eshopping.goods.Goods;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsListViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Goods> goodsList;
	public final class GoodsItemView {
        public ImageView image;     
        public TextView name;     
        public TextView price; 
        public TextView createtime;     
        public TextView sales; 
        public TextView counts;     
        public TextView describe; 
	}
	
	public GoodsListViewAdapter(Context context, ArrayList<Goods> goodsList) {
		this.context = context;
		this.goodsList = goodsList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		GoodsItemView goodsItenView = null;
		
		if (null == convertView) {
			goodsItenView = new GoodsItemView();
			convertView = LayoutInflater.from(context).inflate(R.layout.goods_list_item, null);
			goodsItenView.image = (ImageView) convertView.findViewById(R.id.image);
			goodsItenView.name = (TextView) convertView.findViewById(R.id.name);
			goodsItenView.price = (TextView) convertView.findViewById(R.id.price);
			goodsItenView.sales = (TextView) convertView.findViewById(R.id.sales);
			goodsItenView.counts = (TextView) convertView.findViewById(R.id.counts);
			goodsItenView.describe = (TextView) convertView.findViewById(R.id.describe);
			goodsItenView.createtime = (TextView) convertView.findViewById(R.id.createtime);
			
			convertView.setTag(goodsItenView);
		} else {
			goodsItenView = (GoodsItemView) convertView.getTag();
		}

		goodsItenView.image.setImageDrawable(Drawable.createFromPath(goodsList.get(position).getGoodsImgList().get(0)));
		goodsItenView.name.setText(goodsList.get(position).getName());
		goodsItenView.price.setText(goodsList.get(position).getPrice() + "");
		goodsItenView.sales.setText(goodsList.get(position).getSales() + "");
		goodsItenView.counts.setText(goodsList.get(position).getCounts() + "");
		goodsItenView.describe.setText(goodsList.get(position).getDescribe());
		goodsItenView.createtime.setText(goodsList.get(position).getCreatetime());
		
		return convertView;
	}

}
