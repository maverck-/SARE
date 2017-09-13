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
import tipoInv.TipoInversion;
import tipoInv.GestorDatos;
//import java.util.*;

public class ProyectoBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoDato {
        // TODO code application logic here

        /*GestorDatos Legacy = new GestorDatos("DatosSistemaLegacy.xlsx");*/
        GestorDatos legacy = new TipoInversion("DatosSistemaLegacy.xlsx", "31-dic-2014", "25-dic-2019", "FM"); //Tipo: GR=TipoInversion, FM=Fondos Mutuos, etc.
        legacy = new Histograma(legacy);
        String[][] p = legacy.informe();
        legacy = new OtrosDatos(legacy);
        String[][] q = legacy.informe();
        
        legacy.getArchivo().Escritura(p, q);

    }

}
