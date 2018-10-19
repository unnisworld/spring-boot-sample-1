package com.mytaxi.datatransferobject;

import com.mytaxi.domainvalue.OnlineStatus;

public class DriverSearchDTO 
{
	private String usernameContains;
	
	private OnlineStatus onlineStatus;
	
	private String licensePlateContains;
	
	private Float ratingGreaterThan;


	public String getUsernameContains() {
		return usernameContains;
	}

	public void setUsernameContains(String usernameContains) {
		this.usernameContains = usernameContains;
	}

	public String getLicensePlateContains() {
		return licensePlateContains;
	}

	public void setLicensePlateContains(String licensePlateContains) {
		this.licensePlateContains = licensePlateContains;
	}

	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(OnlineStatus onlineStatus) {
		this.onlineStatus = onlineStatus;
	}
	
	public Float getRatingGreaterThan() {
		return ratingGreaterThan;
	}

	public void setRatingGreaterThan(Float rating) {
		this.ratingGreaterThan = rating;
	}

	@Override
	public String toString() {
		return "DriverSearchDTO [usernameContains=" + usernameContains + ", onlineStatus=" + onlineStatus
				+ ", licensePlateContains=" + licensePlateContains + ", rating=" + ratingGreaterThan + "]";
	}
	
}
