package com.mhalan.tsp;

import java.util.LinkedList;
import java.util.Queue;

public class TabuList {
    private Queue<Move> list = new LinkedList<Move>();
    private int tabu_maxSize = 4;
    Move n = new Move();

    public TabuList() {
    }

    public void tabu(Move m) {
        if (this.list.size() >= tabu_maxSize) {
            n = this.list.remove();
        }
        this.list.add(m);
    }

    public void delete(Move m) {
        this.list.remove(m);
    }

    public boolean isTabuElem(Move move) {
        if (!this.list.contains(move)) {
            return false;
        } else return true;
    }

    public int getMaxSize() {
        return tabu_maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.tabu_maxSize = maxSize;
    }

 }
