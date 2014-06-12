package com.psm.ui;
import java.util.ArrayList;

import com.psm.farmacy.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemHomeMenuAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<ItemHomeMenu> itemList;	
	
	public ItemHomeMenuAdapter(Activity acticity,ArrayList<ItemHomeMenu> array)
	{
		this.activity=acticity;
		this.itemList= array;
	}
	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public Object getItem(int index) {
		return itemList.get(index);
	}

	@Override
	public long getItemId(int index) {		
		return  itemList.get(index).getOptionId();
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		View v = convertView;
		if(convertView == null){
			LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inf.inflate(R.layout.item_home_menu, null);
		}
		ItemHomeMenu item=itemList.get(index);
		ImageView icon = (ImageView) v.findViewById(R.id.imgOpcionIcon);
		TextView option = (TextView)v.findViewById(R.id.txtOpcion);
		option.setText(item.getOption());
		icon.setImageDrawable(item.getOptionIcon());
		return v;
	}

}
