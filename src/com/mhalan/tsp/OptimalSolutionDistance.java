package com.mhalan.tsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class OptimalSolutionDistance {

    private ArrayList<String> optimalRoute = new ArrayList<>();
    private ArrayList<City> cityList = new ArrayList<>();
    //private TravelManager travelManager = new TravelManager();

    public void run(String tspFilePath, String optimalTourPath) throws IOException {
        OptimalSolutionDistance optimalSolutionDistance = new OptimalSolutionDistance();
//        optimalSolutionDistance.loadFile("/data/tsp/ulysses22.opt.tour", true);
//        optimalSolutionDistance.loadFile("/data/tsp/ulysses22.tsp", false);
        optimalSolutionDistance.loadFile(optimalTourPath, true);
        optimalSolutionDistance.loadFile(tspFilePath, false);

        double optimalDistance = 0;
        int city1Id;
        int city2Id;
        int routCount = optimalSolutionDistance.getOptimalRoute().size() - 1;
        for (int i = 0; i < routCount; i++) {
            city1Id = Integer.valueOf(optimalSolutionDistance.getOptimalRoute().get(i)) - 1;
            City fromCity = optimalSolutionDistance.getCityList().get(city1Id);
            city2Id = Integer.valueOf(optimalSolutionDistance.getOptimalRoute().get(i + 1)) - 1;
            City toCity = optimalSolutionDistance.getCityList().get(city2Id);
            optimalDistance += Helper.distance(fromCity, toCity);
        }
        optimalDistance += Helper.distance(optimalSolutionDistance.getCityList().get(Integer.parseInt(optimalSolutionDistance.getOptimalRoute().get(routCount)) - 1),
                optimalSolutionDistance.getCityList().get(Integer.parseInt(optimalSolutionDistance.getOptimalRoute().get(0)) - 1));
        System.out.println("|************************************************************************************|");
        System.out.println("| Calculated Optimal Distance is " + optimalDistance + " it has "
                + optimalSolutionDistance.calculatePercentage(optimalDistance,7013) + "% accuracy|");
        System.out.println("|************************************************************************************|");
    }

    public ArrayList<String> getOptimalRoute() {
        return optimalRoute;
    }

    public void setOptimalRoute(ArrayList<String> optimalRoute) {
        this.optimalRoute = optimalRoute;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }

    @SuppressWarnings("unchecked")
    public void loadFile(String filename, boolean isOptimalList) throws IOException {

        Helper helper = new Helper();
        Map cityListFromFile = helper.readFile(filename, isOptimalList);
        Iterator iterator = cityListFromFile.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (isOptimalList) {
                if (key.equals("optimalRoute")) {
                    ArrayList<String> value = (ArrayList<String>) cityListFromFile.get(key);
                    optimalRoute.addAll(value);
                }
            } else if (key.equals("cityList")) {
                ArrayList<City> value = (ArrayList<City>) cityListFromFile.get(key);
                for (City c : value) {
                    cityList.add(new City(c.getCityID(), c.getLatitude(), c.getLongitude()));
                }
            }
        }
    }

    public double calculatePercentage(double obtained, double total) {
        double result = obtained * 100 / total;
        if (result > 100)
            result = result - 100;
        return (100 - result) ;
    }

/*
    public TravelManager getInitialSolutionUsingOptimalRoute(String tspFilePath, String optimalTourPath) throws IOException {
        OptimalSolutionDistance optimalSolutionDistance = new OptimalSolutionDistance();
        optimalSolutionDistance.loadFile(optimalTourPath, true);
        optimalSolutionDistance.loadFile(tspFilePath, false);
        int city1Id;
        int routCount = optimalSolutionDistance.getOptimalRoute().size();
        for (int i = 0; i < routCount; i++) {
            city1Id = Integer.valueOf(optimalSolutionDistance.getOptimalRoute().get(i)) - 1;
            City city = optimalSolutionDistance.getCityList().get(city1Id);
            travelManager.addCity(city);
        }
        return travelManager;
    }
*/

}
