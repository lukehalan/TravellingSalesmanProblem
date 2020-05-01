package com.mhalan.tsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class TabuSearch {

    private TravelManager travelManager = new TravelManager();

    public TabuSearch() {

    }

    public void run() {

        int numberOfIterations = 2000;
        TabuList tabuList = new TabuList();
        Travel tabuSolution = new Travel(travelManager);
        tabuSolution.candidateTravel();
        City swapCity1 = new City();
        City swapCity2 = new City();

        System.out.println("|***************************************************************");
        System.out.println("First route: " + tabuSolution);
        System.out.println("First distance " + tabuSolution.getTotalDistance());

        Travel best = new Travel(tabuSolution.getTravel());
        double best_des = tabuSolution.getTotalDistance();

        Travel newTabuSolution;
        while (numberOfIterations > 0) {
            for (int i = 1; i < tabuSolution.travelSize() - 1; i++) {
                //for (int j = 2; j < tabuSolution.travelSize() - 1; j++) {
                for (int j = i+1; j < tabuSolution.travelSize(); j++) {
                   // if (i == j) {
                    if (j-i >= tabuSolution.travelSize()-2) {
                        continue;
                    } else {
                        swapCity1 = new City(tabuSolution.getCity(i).getCityID(), tabuSolution.getCity(i).getLatitude(), tabuSolution.getCity(i).getLongitude());
                        swapCity2 = new City(tabuSolution.getCity(j).getCityID(), tabuSolution.getCity(j).getLatitude(), tabuSolution.getCity(j).getLongitude());

                        newTabuSolution = new Travel(tabuSolution.getTravel());
                        newTabuSolution.setCity(i, swapCity2);
                        newTabuSolution.setCity(j, swapCity1);

                        double new_des = newTabuSolution.getTotalDistance();

                        if (new_des < best_des) {
                            Move m = new Move();
                            m.setMove(swapCity1, swapCity2);
                            if (!tabuList.isTabuElem(m)) {
                                best_des = new_des;
                                best = new Travel(newTabuSolution.getTravel());
                                tabuSolution = new Travel(newTabuSolution.getTravel());
                                tabuList.tabu(m);
                            } else if (tabuList.isTabuElem(m) && new_des < best_des) {
                                best_des = new_des;
                                best = new Travel(newTabuSolution.getTravel());
                                tabuSolution = new Travel(newTabuSolution.getTravel());
                                tabuList.delete(m);
                                tabuList.tabu(m);
                            }
                        }
                    }
                }
            }
            numberOfIterations--;
        }
        System.out.println("Best route: " + best);
        System.out.println("Best distance: " + best.getTotalDistance());
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
