package com.ynov.oop;

import com.ynov.oop.player.Player;
import com.ynov.oop.server.*;

import java.util.ArrayList;

public class Game {
    private Player actualPlayer;
    private int turn = 0;
    private Player player1;
    private Player player2;
    private ArrayList<Server> listOfServer = new ArrayList<>();

    public Game(String player1Name, String player2Name) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.actualPlayer = player1;
        mapInit();
    }


    //Iniatilise la map avec les serveur.
    public void mapInit(){
        
    }
    /**
     * Execute les actions de fin de tour pour le joueur actuel
     * puis passe au tour suivant
    */
    public void endTurn() {
        if (actualPlayer != player1){
            actualPlayer = player1;
        } else {
            actualPlayer = player2;
        }
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
        return new ArrayList<String>();
    }

    /**
     * Vérifie si le joueur actuel possède le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public boolean serverBelongsToCurrentPlayer(String ipAddress) {
        return false;
    }

    /**
     * Vérifie si le joueur actuel connait le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public boolean currentPlayerKnowServer(String ipAddress) {
        return false;
    }

    /**
     * Retourne le type du serveur auquel appartient l'adresse ip passée en paramètre
     */
    public String getServerType(String ipAddress) {
        return "";
    }

    /**
     * Vérifie si le serveur auquel appartient l'adresse ip passée en paramètre est chiffré
     */
    public boolean serverIsEncrypted(String ipAddress) {
        return false;
    }

    /**
     * Retourne le nom du joueur qui possède le serveur auquel appartient l'adresse ip passée en paramètre
     */
    public String getServerOwnerName(String ipAddress) {
        return "";
    }

    /**
     * Retourne la liste des address ip que le serveur auquel appartient l'adresse ip passée en paramètre connait
     */
    public ArrayList<String> getKnownServer(String ipAddress) {
        return new ArrayList<String>();
    }

    /**
     * Exécute l'action passée en paramètre sur le serveur auquel appartient l'adresse ip passée en paramètre
     * Retourne vrai si l'action a été exécutée, sinon faux
     * @param action valeur possible: "encrypt", "decrypt", "serverDominance", "networkTrafficAnalysis"
     */
    public boolean executeActionOnServer(String action, String ipAddress) {
        return false;
    }

    /**
     * Vérifie si l'action passée en paramètre peut être exécutée sur le serveur auquel appartient l'adresse ip passée en paramètre
     * @param action valeur possible: "encrypt", "decrypt", "serverDominance", "networkTrafficAnalysis"
     */
    public boolean canExecuteActionOnServer(String action, String ipAddress) {
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

    }

    /**
     * Vérifie si le joueur actuel a gagné
     */
    public boolean currentPlayerWin() {
        return false;
    }
}
