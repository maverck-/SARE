/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listas;

/**
 *
 * @author Eduardo
 */
import java.util.ArrayList;

public class Cliente {

    private ArrayList<Inversion> inversiones;
    private String cod_cli; //key
    private String nom_cli;
    private int cant_inv;

    public Cliente(String cod, String nom) {
        cod_cli = cod;
        nom_cli = nom;
        inversiones = new ArrayList();
        cant_inv = 0;
    }

    public String getCod() {
        return cod_cli;
    }

    public String getNom() {
        return nom_cli;
    }

    public int getCant() {
        return cant_inv;
    }

    void agregarInversion(InversionCliente inv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
