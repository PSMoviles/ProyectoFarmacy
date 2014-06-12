package com.psm.ui;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.*;

public class FarmacyItemOverlay  extends ItemizedOverlay{

	private ArrayList<OverlayItem> Overlays = new ArrayList<OverlayItem>();
	private Context mContext;
	
	public FarmacyItemOverlay(Drawable imagen,Context context) {
		super(imagen);
		mContext = context;		
	}

	@Override
	protected OverlayItem createItem(int i) {
		return Overlays.get(i);
	}

	@Override
	public int size() {		
		return Overlays.size();
		//return 0;
	}
	public void addOverlay(OverlayItem overlay) {
		Overlays.add(overlay);
	    populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  OverlayItem item = Overlays.get(index);
	  AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  dialog.setTitle(item.getTitle());
	  dialog.setMessage(item.getSnippet());
	  dialog.show();
	  return true;
	}
}
