package com.ynov.oop;

import com.ynov.oop.player.Player;
import com.ynov.oop.server.*;
import com.ynov.oop.server.DataServer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private Player actualPlayer;
    private Player player1;
    private Player player2;
    private ArrayList<Server> listOfServer = new ArrayList<>();

    public Game(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.actualPlayer = player1;
        
        Server heartServer1 = new HeartServer(50);
        Server heartServer2 = new HeartServer(50);
        Server dataServer1 = new DataServer(20);
        Server dataServer2 = new DataServer(20);
        Server dataServer3 = new DataServer(20);
        Server dataServer4 = new DataServer(20);
        Server dataServer5 = new DataServer(20);
        Server dataServer6 = new DataServer(20);
        Server encryptionServer1 = new EncryptionServer(0);
        Server encryptionServer2 = new EncryptionServer(0);
        //owner
        heartServer1.setOwner(player1);
        heartServer2.setOwner(player2);
        player1.addVisibleServer(new ArrayList<>(List.of(dataServer2,dataServer1,encryptionServer1,heartServer1)));
        player2.addVisibleServer(new ArrayList<>(List.of(dataServer6,dataServer5,encryptionServer2)));
        player1.addOwnedServer(new ArrayList<>(List.of(heartServer1)));
        

        //listOfserver
        listOfServer.addAll(Arrays.asList(heartServer1,heartServer2,dataServer1,dataServer2,dataServer3,dataServer4,dataServer5,dataServer6,encryptionServer1,encryptionServer2));

        //server connected with each other.
        heartServer1.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer1,dataServer2,encryptionServer1)));
       
        dataServer1.addConnectedServer(new ArrayList<>(Arrays.asList(heartServer1,dataServer2,encryptionServer1,dataServer3,dataServer4)));
       
        dataServer2.addConnectedServer(new ArrayList<>(Arrays.asList(heartServer1,dataServer1,encryptionServer1,dataServer3,dataServer4)));
       
        encryptionServer1.addConnectedServer(new ArrayList<>(Arrays.asList(heartServer1,dataServer1,dataServer2,dataServer3,dataServer4)));
        
        dataServer3.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer1,dataServer2,encryptionServer1,dataServer4,dataServer5,dataServer6,encryptionServer2)));
    
        dataServer4.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer1,dataServer2,encryptionServer1,dataServer3,dataServer5,dataServer6,encryptionServer2)));

        dataServer5.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer3,dataServer4,dataServer6,encryptionServer2,heartServer2)));

        dataServer6.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer3,dataServer4,dataServer5,encryptionServer2,heartServer2)));

        encryptionServer2.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer3,dataServer4,dataServer5,dataServer6,heartServer2)));

        heartServer2.addConnectedServer(new ArrayList<>(Arrays.asList(dataServer5,dataServer6,encryptionServer2)));
    }
    
    public void endTurn() {
        if (actualPlayer != player1){
            actualPlayer = player1;
        } else {
            actualPlayer = player2;
        }
    }

    public Server getServerByIp(String ip){
        for (Server server : listOfServer) {
            if(server.getIp().equals(ip)){
                return server;
            }
            return new Server();
        }
        return null;
    }


    /**
     * Retourne le nom du joueur actuel
     */
    public String getCurrentPlayerName() {
        return actualPlayer.getName();
    }

    /**
     * Retourne la puissance de traitement du joueur actuel
     */
    public int getCurrentPlayerHashCalcul() {
        return actualPlayer.getHashCalcul();
    }

    /**
     * Retourne le nombre de clés de chiffrement du joueur actuel
     */
    public int getCurrentPlayerEncryptionKey() {
        return actualPlayer.getEncryptionKey();
    } 
    /**
     * retourne la liste des address ip des serveurs connus du joueur actuel
     */
    public ArrayList<String> getCurrentPlayerKnownServers() {
        ArrayList<String> serverIP = new ArrayList<String>();
        for(Server server : actualPlayer.getVisibleServer()) {
            serverIP.add(server.getIp());
        }
        return serverIP;
    }

    /**
     * Vérifie si le joueur actuel possède le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public boolean serverBelongsToCurrentPlayer(String ipAddress) {
        ArrayList<String> serversIps = this.getCurrentPlayerKnownServers();
        return serversIps.contains(ipAddress);
    }

    /**
     * Vérifie si le joueur actuel connait le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public boolean currentPlayerKnowServer(String ipAddress) {
        Server serverKnow = getServerByIp(ipAddress);
        if(actualPlayer.getVisibleServer().contains(serverKnow)) return true;
        return false;
    } 

    /**
     * Retourne le type du serveur auquel appartient l'adresse ip passée en paramètre
     */
    public String getServerType(String ipAddress) {
        if(getServerByIp(ipAddress).getClass()==DataServer.class) return "DataServer";
        else if(getServerByIp(ipAddress).getClass()==HeartServer.class) return "HeartServer";
        else {
             return "EncryptionServer";
        }
    }
    /**
     * Vérifie si le serveur auquel appartient l'adresse ip passée en paramètre est chiffré
     */
    public boolean serverIsEncrypted(String ipAddress) {
        return getServerByIp(ipAddress).getIfEncrypted();
    }

    /**
     * Retourne le nom du joueur qui possède le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public String getServerOwnerName(String ipAddress) {
        if(getServerByIp(ipAddress).getOwner() == null){
            return "Owner not found";
        } else if(getServerByIp(ipAddress).getOwner().equals(player1)){
            return player1.getName();
        } else{
            return player2.getName();
        }
    }

    /**
     * Retourne la liste des address ip que le serveur auquel appartient l'adresse ip passée en paramètre connait
     */
    public ArrayList<String> getKnownServer(String ipAddress) {
        ArrayList<String> ips = new ArrayList<>();
        for( Server server2 : getServerByIp(ipAddress).getConnectedServer()) {
            ips.add(server2.getIp());
        }
        return ips;
    }
     
    /**
     * Exécute l'action passée en paramètre sur le serveur auquel appartient l'adresse ip passée en paramètre
     * Retourne vrai si l'action a été exécutée, sinon faux
     * @param action valeur possible: "encrypt", "decrypt", "serverDominance", "networkTrafficAnalysis"
    */
    public boolean executeActionOnServer(String action, String ipAddress) {
        Player p = actualPlayer;
        if(action.equals("encrypt")){
            if(canExecuteActionOnServer(action,ipAddress)){
                p.setEncryptionKey(p.getEncryptionKey() - 1);
                p.setHashCalcul(p.getHashCalcul() - 60);
                return true;
            }else if(action.equals("decrypt")){
                if(canExecuteActionOnServer(action,ipAddress)){

                }
            }else if(action.equals("serverDominance")){
                if(canExecuteActionOnServer(action,ipAddress)){

                }
            }else if(action.equals("networkTrafficAnalysis")){
                if(canExecuteActionOnServer(action,ipAddress)){

                }
            }
        }
        return false;
       }

    /**
     * Vérifie si l'action passée en paramètre peut être exécutée sur le serveur auquel appartient l'adresse ip passée en paramètre
     * @param action valeur possible: "encrypt", "decrypt", "serverDominance", "networkTrafficAnalysis"
     */
    public boolean canExecuteActionOnServer(String action, String ipAddress) {
        Player p = actualPlayer;
            if(action.equals("encrypt")){
                for(Server server: actualPlayer.getOwnedServer()){
                    if(actualPlayer.getClass().toString()=="test"){
                        return true;
                    }
                }
                    if(p.getHashCalcul()>60 && p.getEncryptionKey()>1){
                        return true;
                }
        }else if(action.equals("decrypt")){
            return false;
        }else if(action.equals("serverDominance")){
            return false;
        }else if(action.equals("networkTrafficAnalysis")){
            return false;
        }
        
        return false;
    }

    /**
     * Vérifie si le joueur actuel a déjà analysé le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public boolean currentPlayerHasAnalysedServer(String ipAddress) {
        return false;
    }

    /**
     * Execute les actions de début de tour pour le joueur actuel
     */
    public void startTurn() {
        actualPlayer.setHashCalcul(0);
        actualPlayer.getResources();
        currentPlayerWin();
    }

    /**
     * Vérifie si le joueur actuel a gagné
     */
    public boolean currentPlayerWin() {
        if(actualPlayer.getOwnedServer().size() == listOfServer.size()){
            return true;
        }
        return false;
    }
}
