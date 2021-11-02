package edu.upc.dsa;

import edu.upc.dsa.Comanda;

import java.util.LinkedList;
import java.util.List;

public class Usuari {

    List<Comanda> llistaComandes = new LinkedList<Comanda>();
    List<Comanda> llistaComandesServides = new LinkedList<Comanda>();
    String usuariID;
    double numcomandes;
    String nomusuari;

    public String getNomUsuari(){
        return this.nomusuari;
    }

    public void setUsuari(String nom) {
        this.nomusuari = nom;
    }
    public Usuari (String nomusuari, String usuariID) {
        this.nomusuari = nomusuari;
        this.usuariID=usuariID;
        this.numcomandes=0;
    }

    public String getUsuariID(){
            return this.usuariID;
    }
    public void afegirComanda(Comanda comanda){

        this.llistaComandesServides.add(comanda);
    }

    public List<Comanda> getLlistaComandesServides(){
            return this.llistaComandesServides;
        }

    }

