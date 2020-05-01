package com.mhalan.tsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class SimulatedAnnealing {

    private TravelManager travelManager = new TravelManager();

    public void run() throws IOException {
        double initialTemp = 100000;
        double coolingRate = 0.006;
        Travel solution = new Travel(travelManager);
        solution.candidateTravel();
        System.out.println("|***************************************************************");
        System.out.println("First route: " + solution);
        System.out.println("First distance: " + solution.getTotalDistance());
        Travel bestRoute = new Travel(solution.getTravel());
        int iteration = 0;
        // while (initialTemp > 1) {
        while (iteration < 2000 || initialTemp > 1) {
            Travel freshSolution = new Travel(solution.getTravel());
            int travelPosition1 = Helper.randomInt(0, freshSolution.travelSize());
            int travelPosition2 = Helper.randomInt(0, freshSolution.travelSize());

            while (travelPosition1 == travelPosition2) {
                travelPosition2 = Helper.randomInt(0, freshSolution.travelSize());
            }

            City swapCity1 = freshSolution.getCity(travelPosition1);
            City swapCity2 = freshSolution.getCity(travelPosition2);
            freshSolution.setCity(travelPosition2, swapCity1);
            freshSolution.setCity(travelPosition1, swapCity2);
            double current = solution.getTotalDistance();
            double neighbour = freshSolution.getTotalDistance();
            double rand = Helper.randomDouble();
            if (Helper.acceptanceProbability(current, neighbour, initialTemp) > rand) {
                solution = new Travel(freshSolution.getTravel());
            }
            if (solution.getTotalDistance() < bestRoute.getTotalDistance()) {
                bestRoute = new Travel(solution.getTravel());
            }
            initialTemp *= 1 - coolingRate;
            iteration++;
        }
        System.out.println("Best route: " + bestRoute);
        System.out.println("Best distance: " + bestRoute.getTotalDistance());
        System.out.println("|***************************************************************");
    }

    @SuppressWarnings("unchecked")
    public void loadFile(String filename) throws IOException {
        Helper helper = new Helper();
        Map cityListFromFile = helper.readFile(filename, false);
        Iterator iterator = cityListFromFile.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (key.equals("cityList")) {
                ArrayList<City> value = (ArrayList<City>) cityListFromFile.get(key);
                for (City c : value) {
                    travelManager.addCity(new City(c.getCityID(), c.getLatitude(), c.getLongitude()));
                }
            }
        }
    }

}
