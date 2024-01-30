package com.ynov.oop.player;

import java.util.ArrayList;
import com.ynov.oop.server.*;

public class Player {
    //atribut
    private int hashCalcul;
    private int encryptionKey;
    private String name;
    private ArrayList<Server> visibleServer = new ArrayList<>();
    private ArrayList<Server> ownedServers = new ArrayList<>();
    
    public Player(String name){
        this.hashCalcul = 0;
        this.name = name;
        this.visibleServer = new ArrayList<>();
        this.encryptionKey = 0;
    }

    //getter -------------------------------------------------------
    public String getName(){
        return name;
    }
    public int getHashCalcul(){
        return hashCalcul;
    }
    public int getEncryptionKey(){
        return encryptionKey;
    }
    public ArrayList<Server> getVisibleServer(){
        return visibleServer;
    }
    
    public ArrayList<Server> getOwnedServer(){
        return ownedServers;
    }

    //setter ---------------------------------------------------------------------------
    public void setName(String newname){
        this.name = newname;
    }
    public void setHashCalcul(int nbHash){
        this.hashCalcul = nbHash;
    }
    public void setEncryptionKey(int nbEncryptionKey){
        this.encryptionKey = nbEncryptionKey;
    }
    public void addVisibleServer(ArrayList<Server> dataServer2){
        this.visibleServer.addAll(dataServer2);
    }
    public void addOwnedServer(ArrayList<Server> heartServer1){
        this.ownedServers.addAll(heartServer1);
    }
   
    public void getResources(){
        for (Server server : ownedServers) {
            if(server.getClass() == HeartServer.class){
                this.hashCalcul += 50;
            } else if(server.getClass() == DataServer.class){
                this.hashCalcul += 20;
            } else{
                if(hashCalcul >= 40) {
                    this.hashCalcul -= 40;
                    this.encryptionKey += 1;
                }
            }    
        }
    }
}
 
