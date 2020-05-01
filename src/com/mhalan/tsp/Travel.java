package com.mhalan.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Travel {

    private TravelManager travelManager = new TravelManager();
    private ArrayList<City> travel = new ArrayList<City>();
    private int distance = 0;


    public Travel(TravelManager tm){
        this.travelManager = tm;
        for (int i = 0; i < travelManager.CitiesCount(); i++) {
            travel.add(null);
        }
    }

    @SuppressWarnings("unchecked")
	public Travel(ArrayList<City> travel){
        this.travel = (ArrayList<City>) travel.clone();
    }
    
    public ArrayList<City> getTravel(){
        return travel;
    }
     
     public void candidateTravel() {
        for (int cityId = 0; cityId < travelManager.CitiesCount(); cityId++) {
            setCity(cityId, travelManager.getCity(cityId));
        }
        Collections.shuffle(travel);
    }

    public City getCity(int id) {
        return travel.get(id);
    }

     public void setCity(int id, City city) {
        travel.set(id, city);
        distance = 0;
    }
    
     public double getTotalDistance(){
    	if (distance == 0) {
            int travelDistance = 0;
            for (int cityId=0; cityId < travelSize(); cityId++) {
                City fCity = getCity(cityId);
                City dCity;
                if(cityId+1 < travelSize()){
                    dCity = getCity(cityId+1);
                }
                else{
                    dCity = getCity(0);
                }
                travelDistance += Helper.distance(fCity, dCity);
            }
            distance = travelDistance;
        }
        return distance;
    }

    public int travelSize() {
        return travel.size();
    }
    
    @Override
    public String toString() {
        String route = getCity(0).getCityID();
        for (int i = 1; i < travelSize(); i++) {
            route += " ==> " + getCity(i).getCityID();
        }
        return route;
    }
}
    
