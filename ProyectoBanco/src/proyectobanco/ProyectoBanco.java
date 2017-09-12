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
        GestorDatos legacy = new General("DatosSistemaLegacy.xlsx", "05-ene-2014", "25-jul-2018", 0);
        legacy = new Histograma(legacy);
        legacy.informe();
        legacy = new OtrosDatos(legacy);
        legacy.informe();

    }

}
