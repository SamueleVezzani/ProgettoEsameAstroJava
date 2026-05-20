package org.example.progettoesameastrojava.Entities;

public class Player {
    private int x,y;
    private Direction dir= Direction.NONE;
    private boolean ismove=false;

    public Player(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void update(GameMap map){
        if(!ismove) return;
        int nextx=this.x+dir.dx;
        int nexty=this.y+dir.dy;

        this.x=nextx;
        this.y=nexty;

    }
}
