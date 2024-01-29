package com.ynov.oop.server;

public class EncryptionServer extends Server{
    private int key;
    
    public EncryptionServer(int powerRate){
        super();
        super.setType("EncryptionServer");
        this.key = 1;
    }
    public int getPowerRate(){
        return this.key;
    }

    public void setPowerRate(int key){
        this.key = key;
    }
    public int getLabor(){
        return this.key;
    }

}
