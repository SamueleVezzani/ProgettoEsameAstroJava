package org.example.progettoesameastrojava.Entities;

public enum Direction {
    UP(0,-1),
    DOWN(0, 1),
    RIGHT(1,0),
    LEFT(-1,0),
    NONE(0,0);

    public final int dx,dy;

    Direction(int dx,int dy){
        this.dx=dx;
        this.dy=dy;
    }
}
