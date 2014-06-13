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

public class ItemHomeListAdapter extends BaseAdapter
{

	protected Activity activity;
    protected ArrayList<ItemHomeList> items;
    
    public ItemHomeListAdapter(Activity activity, ArrayList<ItemHomeList> items) {
        this.activity = activity;
        this.items = items;
      }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return items.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		 if(convertView == null){
	            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            v = inf.inflate(R.layout.item_home_list, null);
	            ItemHomeList dir = items.get(position);
	            ImageView foto = (ImageView) v.findViewById(R.id.dwexcip);
	            foto.setImageDrawable(dir.getIcon());
	            TextView txtMed=(TextView)v.findViewById(R.id.lblMedicinax);
	            TextView txtTra=(TextView)v.findViewById(R.id.lblTratamientox);
	            txtMed.setText(dir.getMedicina());
	            txtTra.setText(dir.getTratamiento());
	                       
	        }
		return v;
	}

}
