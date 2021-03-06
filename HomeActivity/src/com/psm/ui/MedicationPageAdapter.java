package com.psm.ui;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MedicationPageAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;

	public MedicationPageAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int index) {
		return this.fragments.get(index);
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}

}
