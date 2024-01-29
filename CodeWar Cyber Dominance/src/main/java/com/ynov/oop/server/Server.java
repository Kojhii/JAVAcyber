package com.ynov.oop.server;

import java.util.ArrayList;
import com.ynov.oop.player.Player;

public class Server {
    private ArrayList<Server> connectedServer;
    private ArrayList<Player> serverDiscoverPlayers;
    private Player owner;
    private boolean isEncrypted;
    //The ip for the server,it will be random
    private String ip;
    private String type;


    //constructor , owner and encrypted at null /false because the game has not started
    public Server(){
        this.ip = getRandomIpAddress() ;
        this.owner = null;
        this.isEncrypted = false;
        this.connectedServer = new ArrayList<>();
    }
   
    public String getIp(){
        return ip;
    }
    public boolean getIfEncrypted(){
        return isEncrypted;
    }
    public Player getOwner(){
        return owner;
    }
    public void setIp(String newIP){
        this.ip = newIP;
    }
    public ArrayList<Server> getConnectedServer(){
        return connectedServer;
    }
    public ArrayList<Player> getServerDiscoverPlayer(){
        return serverDiscoverPlayers;
    }
   
    //we add , and not "set", because the player will acumulate connected server
    public void addConnectedServer(ArrayList<Server> server){
        this.connectedServer.addAll(server);
    }
    public void setServerDiscoverPlayer(ArrayList<Player> newServerDiscoverPlayer){
        this.serverDiscoverPlayers = newServerDiscoverPlayer;

    }
    
    public void setEncrypted(boolean True){
        this.isEncrypted = True;
    }
    public void setOwner(Player newowner){
        this.owner = newowner;
    }

    public static String getRandomIpAddress() {
        return getRandomInt(0, 255) + "." + getRandomInt(0, 255) + "."
                + getRandomInt(0, 255) + "." + getRandomInt(0, 255);
    }

    public static int getRandomInt() {
        return getRandomInt(1, Integer.MAX_VALUE);
    }
    public static int getRandomInt(int min, int max) {
        return min + (int) ((Math.random() * (max - min)));
    }

    public void setType(String type){
        this.type = type;
    }

    public String getString(){
        return this.type;
    }
}
    

