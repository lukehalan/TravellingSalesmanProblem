package com.mhalan.tsp;

public class City {

	private String cityID;
	private double longitude;
    private double latitude;

	public City() {
	}

	public City(String cityID, double latitude, double longitude){
    	this.cityID = cityID;
		this.latitude = latitude;
        this.longitude = longitude;

    }            
        
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public String getCityID() {
		return cityID;
	}
	public void setCityID(String cityID) {
		this.cityID = cityID;
	}
	
}
