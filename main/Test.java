package main;

import connection.BddObject;

public class Test {
    public static void main(String[] args) {
        try {
            Joueur joueur = new Joueur();
            Object[] personnes = joueur.getData(BddObject.getOracle(), null);
            for (Object object : personnes)
                System.out.println(((Joueur) object).getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}