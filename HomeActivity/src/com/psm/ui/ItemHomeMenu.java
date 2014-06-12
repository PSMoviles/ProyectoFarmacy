package com.psm.ui;
import android.graphics.drawable.*;

public class ItemHomeMenu {
	private String Option;
	private Drawable OptionIcon;
	private long OptionId;
	
	public ItemHomeMenu()
	{}
	public ItemHomeMenu(long id,String option,Drawable icon)
	{
		this.setOption(option);
		this.setOptionIcon(icon);
		this.setOptionId(id);
	}
	public String getOption() {
		return Option;
	}
	public void setOption(String option) {
		Option = option;
	}
	public Drawable getOptionIcon() {
		return OptionIcon;
	}
	public void setOptionIcon(Drawable optionIcon) {
		OptionIcon = optionIcon;
	}
	public long getOptionId() {
		return OptionId;
	}
	public void setOptionId(long optionId) {
		OptionId = optionId;
	}

}
