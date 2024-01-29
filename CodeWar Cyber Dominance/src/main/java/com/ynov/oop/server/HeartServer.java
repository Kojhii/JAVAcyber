package com.ynov.oop.server;

public class HeartServer extends Server{
    private int powerRate;
    
    public HeartServer(int powerRate){
        super();
        super.setType("HeartServer");
        this.powerRate = 50;
    }
    public int getPowerRate(){
        return this.powerRate;
    }

    public void setPowerRate(int powerRate){
        this.powerRate = powerRate;
    }

    public int getLabor(){
        return this.powerRate;
    }
}
