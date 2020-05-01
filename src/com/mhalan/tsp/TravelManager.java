package com.mhalan.tsp;

import java.util.ArrayList;

public class TravelManager {


    private ArrayList<City> destinations = new ArrayList<City>();
	public void addCity(City city) {
		destinations.add(city);
	}
	public City getCity(int id){return (City)destinations.get(id);}
	public int CitiesCount(){return destinations.size();
	}
    
}
