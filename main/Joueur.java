package main;

import connection.BddObject;

public class Joueur extends BddObject {
    String idJoueur;
    String name;

    public String getIdJoueur() {
        return idJoueur;
    }
    
    public String getName() {
        return name;
    }

    public void setIdJoueur(String idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Joueur() {
        this.setCountPK(7);
        this.setPrefix("JR");
        this.setTable("JOUEUR");
    }
}