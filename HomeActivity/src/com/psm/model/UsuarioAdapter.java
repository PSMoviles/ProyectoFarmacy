package com.psm.model;

import java.util.ArrayList;


import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class UsuarioAdapter extends BaseAdapter{
	private ArrayList<Usuario> usuarios;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub		
		return usuarios.size();
	}

	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return usuarios.get(index);
	}

	@Override
	public long getItemId(int index) {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}
