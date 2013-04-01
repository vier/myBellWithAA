package net.monoboy.model;

import android.util.Log;

public class FlurryActiveUser {

	private Integer userCount;
	private String date;

	public FlurryActiveUser(String userCount, String date) {
		super();
		
		setUserCount(userCount);
		setDate(date);
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(String userCount) {
		try {
			this.userCount = Integer.valueOf(userCount);
		} catch (Exception e) {
			Log.e("vier", "invalid user count : " + userCount);
			this.userCount = -1;
		}
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
