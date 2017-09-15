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
import static java.lang.Thread.sleep;
import tipoInv.TipoInversion;
import tipoInv.GestorDatos;
//import java.util.*;

public class ProyectoBanco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NoDato, InterruptedException {
        // TODO code application logic here
        Menu x = new Menu();
        //Mostramos el menú
        x.setVisible(true);
        do{
            sleep(1000);
            System.out.print(".");
        }while(!x.getEstado());
        System.out.println("\n Su consulta fué realizada para las siguientes fechas:"
                + "\n Fecha inicial: "+ x.getFechaInicio()
                + "\n Fecha final: "+ x.getFechaFin()
                + "\n COMIENZO PROGRAMA");
        x.setVisible(false);
        GestorDatos legacy = new TipoInversion("DatosSistemaLegacy.xlsx", x.getFechaInicio(), x.getFechaFin(), x.getTipo());
        legacy = new TablaHistograma(legacy);
        String[][] p = legacy.informe();
        legacy = new TendenciaCentral(legacy);
        String[][] q = legacy.informe();
        //Escribimos el informe en el archivo de salida (Excel)
        legacy.getArchivo().Escritura(p, q);
        x.cambiarEstado();

    }

}
