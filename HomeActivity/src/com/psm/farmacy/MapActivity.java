package com.psm.farmacy;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import com.google.android.maps.*;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.widget.Toast;

import com.psm.ui.*;

public class MapActivity extends com.google.android.maps.MapActivity {
	
	//private FarmacyItemOverlay  	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		try
		{
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);    
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.bottle);
	    FarmacyItemOverlay itemizedoverlay = new FarmacyItemOverlay(drawable, this);
	    
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
	    
	    itemizedoverlay.addOverlay(overlayitem);
	    mapOverlays.add(itemizedoverlay);
	    
	    Toast.makeText(this, "Geopoint cargado correctamente", Toast.LENGTH_LONG);
	    
		}
		catch(Exception ex)
		{
			Toast.makeText(this, "Error al cargar geopoint", Toast.LENGTH_LONG);
		}
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub		
		return false;
	}

}
