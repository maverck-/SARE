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
            IOExcel archivo = new IOExcel(arch);
            datos = archivo.LegacyLector();
            ini = i;
            fin = f;
            tip = t;
            filtro = Filtro();
        } catch (NullPointerException e) {
            System.out.println("El archivo no existe.");
        }
    }
    
    public void informe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ArrayList getFiltro(){
        return filtro;
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

    protected ArrayList[] FiltrarTipo() {
        //4 tipos
        
        ArrayList[] filtroTipo = new ArrayList[4];
        filtroTipo[0] = new ArrayList();
        filtroTipo[1] = new ArrayList();
        filtroTipo[2] = new ArrayList();
        filtroTipo[3] = new ArrayList();

        datos.setFirst();
        while (!datos.eol()) {
            //if(((Inversion)datos.currValue()).getTipo()!=null){
            switch (((Inversion) datos.currValue()).getTipo()) {
                case "DF":
                    filtroTipo[0].add(datos.currValue());
                    break;
                case "FM":
                    filtroTipo[1].add(datos.currValue());
                    break;
                case "FI":
                    filtroTipo[2].add(datos.currValue());
                    break;
                case "BO":
                    filtroTipo[3].add(datos.currValue());
                    break;
            }
            //}
            datos.next();
        }
        quickSort(filtroTipo[0], 0, filtroTipo[0].size() - 1);
        quickSort(filtroTipo[1], 0, filtroTipo[1].size() - 1);
        quickSort(filtroTipo[2], 0, filtroTipo[2].size() - 1);
        quickSort(filtroTipo[3], 0, filtroTipo[3].size() - 1);

        return filtroTipo;
    }

    protected Date ConvertirFecha(String fecha) {
        if (fecha == null) {
            return null;
        }
        String[] nuevaF = fecha.split("-");
        switch (nuevaF[1]) {
            case "ene":
                nuevaF[1] = 0 + "";
                break;
            case "feb":
                nuevaF[1] = 1 + "";
                break;
            case "mar":
                nuevaF[1] = 2 + "";
                break;
            case "abr":
                nuevaF[1] = 3 + "";
                break;
            case "may":
                nuevaF[1] = 4 + "";
                break;
            case "jun":
                nuevaF[1] = 5 + "";
                break;
            case "jul":
                nuevaF[1] = 6 + "";
                break;
            case "ago":
                nuevaF[1] = 7 + "";
                break;
            case "sep":
                nuevaF[1] = 8 + "";
                break;
            case "oct":
                nuevaF[1] = 9 + "";
                break;
            case "nov":
                nuevaF[1] = 10 + "";
                break;
            case "dic":
                nuevaF[1] = 11 + "";
                break;
            case "jan":
                nuevaF[1] = 0 + "";
                break;
            case "Jan":
                nuevaF[1] = 0 + "";
                break;
            case "Feb":
                nuevaF[1] = 1 + "";
                break;
            case "Mar":
                nuevaF[1] = 2 + "";
                break;
            case "Apr":
                nuevaF[1] = 3 + "";
                break;
            case "May":
                nuevaF[1] = 4 + "";
                break;
            case "Jun":
                nuevaF[1] = 5 + "";
                break;
            case "Jul":
                nuevaF[1] = 6 + "";
                break;
            case "Agu":
                nuevaF[1] = 7 + "";
                break;
            case "Aug":
                nuevaF[1] = 7 + "";
                break;
            case "Sep":
                nuevaF[1] = 8 + "";
                break;
            case "Oct":
                nuevaF[1] = 9 + "";
                break;
            case "Nov":
                nuevaF[1] = 10 + "";
                break;
            case "Dic":
                nuevaF[1] = 11 + "";
                break;
            case "Dec":
                nuevaF[1] = 11 + "";
                break;
        }
        Date nfecha = new Date(Integer.parseInt(nuevaF[2]) - 1900, Integer.parseInt(nuevaF[1]), Integer.parseInt(nuevaF[0]));
        return nfecha;
    }

    protected int Get(ArrayList e, int i) {
        return ((Inversion) e.get(i)).getMontoI();
    }

    protected void quickSort(ArrayList e, int izq, int der) {
        Inversion piv = (Inversion) e.get(izq);
        int pivote = Get(e, izq);
        int i = izq;
        int j = der;
        Inversion auxIntercambio;
        while (i < j) {
            while (Get(e, i) <= pivote && i < j) {
                i++;
            }
            while (Get(e, j) > pivote) {
                j--;
            }
            if (i < j) {
                auxIntercambio = (Inversion) e.get(i);
                e.set(i, e.get(j));
                e.set(j, auxIntercambio);
            }
        }
        e.set(izq, e.get(j));
        e.set(j, piv);
        if (izq < j - 1) {
            quickSort(e, izq, j - 1);
        }
        if (j + 1 < der) {
            quickSort(e, j + 1, der);
        }
    }

}
