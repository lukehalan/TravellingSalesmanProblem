package com.mhalan.tsp;

public class Move {
    private City city1;
    private City city2;

    public Move() {

    }

    public void setMove(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;
    }

    public void setCity1(City city1) {
        this.city1 = city1;
    }

    public void setCity2(City city2) {
        this.city1 = city2;
    }

    public City getCity1() {
        City city1 = this.city1;
        return city1;
    }

    public City getCity2() {
        City city2 = this.city2;
        return city2;
    }
}
