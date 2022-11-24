package main;

import connection.BddObject;

public class Personne extends BddObject {
    String idPersonne;
    String name;
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setIdPersonne(String idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getName() {
        return name;
    }
    
    public String getIdPersonne() {
        return idPersonne;
    }
    
    public Personne(String idPersonne, String name) {
        this();
        setIdPersonne(idPersonne);
        setName(name);
    }

    public Personne() {
        this.setCountPK(7);
        this.setFunctionPK("getPersonneSeq()");
        this.setPrefix("PRS");
        this.setTable("Personne");
    }

    public Personne(String name) throws Exception {
        this();
        setIdPersonne(buildPrimaryKey(BddObject.getOracle()));
        setName(name);
    }
}