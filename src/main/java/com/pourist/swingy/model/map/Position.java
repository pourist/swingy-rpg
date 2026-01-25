package com.pourist.swingy.model.map;

public class Position {
    final private int x;
    final private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean equals(Position other) {
        return x == other.getX() && y == other.getY();
    }
}
