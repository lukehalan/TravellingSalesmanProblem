package com.mhalan.tsp;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static java.lang.Math.acos;

public class Helper {

    public static double convert(double x) {
        double PI = 3.141592;
        double deg = Math.ceil(x);
        double min = x - deg;
        return PI * (deg + 5.0 * min / 3.0) / 180.0;
    }

    public static double distance(City city1, City city2) {
        double distance;
        double eRadius = 6378.388;
        double temp1 = Math.cos(convert(city1.getLongitude()) - convert(city2.getLongitude()));
        double temp2 = Math.cos(convert(city1.getLatitude()) - convert(city2.getLatitude()));
        double temp3 = Math.cos(convert(city1.getLatitude()) + convert(city2.getLatitude()));
        distance = eRadius * acos(0.5 * ((1.0 + temp1) * temp2 - (1.0 - temp1) * temp3)) + 1.0;
        return distance;
    }

    public static double acceptanceProbability(double currentD, double newD, double initialTemp) {
        if (newD < currentD) {
            return 1.0;
        }
        return Math.exp((currentD - newD) / initialTemp);
    }

    public static double randomDouble() {
        Random ranNum = new Random();
        return ranNum.nextInt(1000) / 1000.0;
    }

    public static int randomInt(int minimum, int maximum) {
        Random ranNum = new Random();
        double di = minimum + ranNum.nextDouble() * (maximum - minimum);
        return (int) di;
    }

    @SuppressWarnings("unchecked")
    public Map readFile(String filename, boolean optimalList) throws IOException {

        String name;
        String dimension = "";
        Map map = new HashMap<>();

        FileReader fileReader = new FileReader(filename);
        BufferedReader reader = new BufferedReader(fileReader);

        name = reader.readLine();
        String line = null;
      //  System.out.println(name);

        if (optimalList) {
            ArrayList<String> optimalRoute = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                line = reader.readLine();
                if (line.contains("DIMENSION")){
                    dimension = line.split(":")[1].trim();
                }
            }
            while ((line = reader.readLine()) != null && line != "-1") {
                if (line.contains("-1"))
                    break;
                optimalRoute.add(line);
            }

            map.put("optimalRoute", optimalRoute);
            map.put("dimension",dimension);
            return map;
        } else {
            ArrayList<City> cityList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
               line = reader.readLine();
                if (line.contains("DIMENSION")){
                    dimension = line.split(":")[1].trim();
                  //  System.out.println(line);
                }
            }
            while ((line = reader.readLine()) != null && !line.equals("EOF")) {
                if (line.contains("EOF"))
                    break;
                int lLenght = line.length();
              //  System.out.println(line);
                line = line.substring(1, lLenght);
                String[] stringParts = line.split(" ");
                if (stringParts.length == 3) {
                    String cityID = stringParts[0];
                    cityList.add(new City(cityID, Double.parseDouble(stringParts[1]), Double.parseDouble(stringParts[2])));
                }
            }
            map.put("cityList", cityList);
            map.put("dimension",dimension);
            return map;
        }

    }
}
