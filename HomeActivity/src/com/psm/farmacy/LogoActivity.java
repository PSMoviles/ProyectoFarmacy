package com.psm.farmacy;

import OGL.Render;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class LogoActivity extends Activity {

	
	GLSurfaceView ourSurface;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		ourSurface = new GLSurfaceView(this);
		ourSurface.setRenderer(new Render(this));
		setContentView(ourSurface);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medicine, menu);
		return true;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurface.onPause();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurface.onResume();
	}


}