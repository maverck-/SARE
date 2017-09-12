/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectobanco;

/**
 *
 * @author Eduardo
 */
import estadistica.*;
import listas.NoDato;
import java.io.*;
import tipoInv.General;
import tipoInv.GestorDatos;
//import java.util.*;

public class ProyectoBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoDato {
        // TODO code application logic here

        /*GestorDatos Legacy = new GestorDatos("DatosSistemaLegacy.xlsx");*/
        GestorDatos legacy = new General("DatosSistemaLegacy.xlsx", "01-ene-2013", "25-dic-2019", "GR"); //Tipo: GR=General, FM=Fondos Mutuos, etc.
        legacy = new OtrosDatos(legacy);
        legacy.informe();
        legacy = new Histograma(legacy);
        legacy.informe();

    }

}
