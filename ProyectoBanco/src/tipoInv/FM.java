/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoInv;

import java.io.IOException;
import java.util.ArrayList;
import listas.Lista;
import listas.NoDato;
import proyectobanco.IOExcel;

/**
 *
 * @author Maver
 */
public class FM extends GestorDatos {

    private Lista datos;
    private ArrayList filtroFecha;
    private ArrayList[] filtroTipo;

    public FM(String arch, String i, String f) throws IOException, NoDato {
        IOExcel archivo = new IOExcel(arch);
        datos = archivo.LegacyLector();
        filtroFecha = FiltrarFecha(i, f);
    }

    public void informe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
