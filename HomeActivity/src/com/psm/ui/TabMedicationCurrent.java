package com.psm.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psm.farmacy.R;

public class TabMedicationCurrent extends Fragment {
	 public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
	 
	 public static final TabMedicationCurrent newInstance(String message)
	 {
		TabMedicationCurrent f = new TabMedicationCurrent();
		Bundle bdl = new Bundle(1);
		bdl.putString(EXTRA_MESSAGE, message);
		f.setArguments(bdl);
		return f;
	 }
	 
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
	 {
		 //String message = getArguments().getString(EXTRA_MESSAGE);
		 
		 View v = inflater.inflate(R.layout.tab_medication_current, container, false);
		 //es el aquivalente al onCreate del view
		 //TextView messageTextView = (TextView)v.findViewById(R.id.textView);
		 //messageTextView.setText(message);
		 return v;

	 }
			 

}

