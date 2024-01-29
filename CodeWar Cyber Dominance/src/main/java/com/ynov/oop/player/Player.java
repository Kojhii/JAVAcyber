package com.ynov.oop.player;

import java.util.ArrayList;
import com.ynov.oop.server.*;

public class Player {
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

    public void setName(String newname){
        this.name = newname;
    }
    public void setHashCalcul(int nbHash){
        this.hashCalcul = nbHash;
    }
    public void setEncryptionKey(int nbEncryptionKey){
        this.encryptionKey = nbEncryptionKey;
    }
    public void addVisibleServer(ArrayList<Server> server){
        this.visibleServer.addAll(server);
    }
    public void setOwnedServers(ArrayList<Server> newOwnedServer){
        this.ownedServers = newOwnedServer;
    }


    public void getResources(){
        for (Server server : ownedServers) {
            if()
                
        }
    }
}
