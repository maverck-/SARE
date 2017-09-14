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
    public static void main(String[] args) throws IOException, NoDato, InterruptedException {
        // TODO code application logic here

        /*Menu x = new Menu();
        //Mostramos el menú
        x.setVisible(true);

        Thread.sleep(10000);
        System.out.println("Fin del tiempo");
        System.out.println(x.getFechaInicio());
        System.out.println(x.getFechaFin());
        System.out.println(x.getTipo());

        GestorDatos legacy = new TipoInversion("DatosSistemaLegacy.xlsx", x.getFechaInicio(), x.getFechaFin(), x.getTipo());*/
        GestorDatos legacy = new TipoInversion("DatosSistemaLegacy.xlsx", "01-sep-2017", "31-sep-2017", "GR");
        legacy = new TablaHistograma(legacy);
        String[][] p = legacy.informe();
        legacy = new TendenciaCentral(legacy);
        String[][] q = legacy.informe();
        //x.setVisible(false);
        //Escribimos el informe en el archivo de salida (Excel)
        legacy.getArchivo().Escritura(p, q);

    }

}
