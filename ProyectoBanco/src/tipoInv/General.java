/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoInv;

import estadistica.Calculos;
import listas.NoDato;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import listas.Inversion;
import listas.Lista;
import proyectobanco.IOExcel;

/**
 *
 * @author Maver
 */
public final class General extends GestorDatos {

    private Lista datos;
    private ArrayList filtro;
    private String ini, fin;
    private int tip;
    private IOExcel archivo;

    /*public General(String arch) throws IOException, NoDato {
        IOExcel archivo = new IOExcel(arch);
        datos = archivo.LegacyLector();
        filtroFecha = FiltrarFecha();
        Calculos tabla = new Calculos(filtroFecha);
        MostrarDatosTabla2(tabla.otrosDatos(filtroFecha));
        MostrarDatosTabla2(tabla.tablaDeFrecuencia(filtroFecha));
        filtroTipo = FiltrarTipo();
        MostrarDatosTabla2(tabla.tablaDeFrecuencia(filtroTipo[0]));
        MostrarDatosTabla2(tabla.tablaDeFrecuencia(filtroTipo[1]));
        MostrarDatosTabla2(tabla.tablaDeFrecuencia(filtroTipo[2]));
        MostrarDatosTabla2(tabla.tablaDeFrecuencia(filtroTipo[3]));
        archivo.SalidaExcel(tabla.tablaDeFrecuencia(filtroFecha), tabla.otrosDatos(filtroFecha));
    }*/
    public General(String arch, String i, String f, int t) throws IOException, NoDato {
        try {
            archivo = new IOExcel(arch);
            datos = archivo.LegacyLector();
            ini = i;
            fin = f;
            tip = t;
            filtro = Filtro();
        } catch (NullPointerException e) {
            System.out.println("El archivo no existe.");
        }
    }
    
    public IOExcel getArchivo(){
        return archivo;
    }
    
    @Override
    public void informe() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList Filtro() {
        Date i = ConvertirFecha(ini);
        Date f = ConvertirFecha(fin);
        ArrayList entrega = new ArrayList();
        datos.setFirst();
        while (!datos.eol()) {
            if (((Inversion) datos.currValue()).getFechas().getInicio().after(i)
                    && ((Inversion) datos.currValue()).getFechas().getFinal().before(f)) {
                entrega.add(datos.currValue());
            }
            datos.next();
        }
        quickSort(entrega, 0, entrega.size() - 1);
        return entrega;
    }
}
