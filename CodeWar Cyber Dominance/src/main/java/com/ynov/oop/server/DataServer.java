package com.ynov.oop.server;

public class DataServer extends Server{
    private int powerRate;
    
    public DataServer(int powerRate){
        super();        
        super.setType("DataServer");

        this.powerRate = 20;
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
