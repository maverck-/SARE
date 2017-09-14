/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoInv;

import listas.NoDato;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import listas.Inversion;
import listas.Lista;
import proyectobanco.IOEstrategia;

/**
 *
 * @author Maver
 */
public class TipoInversion extends GestorDatos {

    private Lista datos;
    private ArrayList filtro;
    private String ini, fin;
    private String tip;
    private IOEstrategia archivo;

    public TipoInversion(String arch, String i, String f, String t) throws IOException, NoDato {
        try {
            archivo = new IOEstrategia(arch);
            datos = archivo.Lectura();
            ini = i;
            fin = f;
            tip = t;
            filtro = Filtro();
        } catch (NullPointerException e) {
            System.out.println("El archivo no existe.");
        }
    }

    public IOEstrategia getArchivo() {
        return archivo;
    }

    @Override
    public String[][] informe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList Filtro() {
        Date i = ConvertirFecha(ini);
        Date f = ConvertirFecha(fin);
        ArrayList entrega = new ArrayList();
        ArrayList[] filtroTipo = new ArrayList[4];
        filtroTipo[0] = new ArrayList();
        filtroTipo[1] = new ArrayList();
        filtroTipo[2] = new ArrayList();
        filtroTipo[3] = new ArrayList();

        datos.setFirst();
        while (!datos.eol()) {
            if (((Inversion) datos.currValue()).getFechas().getInicio().after(i)
                    && ((Inversion) datos.currValue()).getFechas().getFinal().before(f)) {
                if (tip.equals("GR")) {
                    entrega.add(datos.currValue());
                } else {
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
                        default:
                            throw new AssertionError();
                    }
                }

            }
            datos.next();
        }
        if (tip.equals("GR")) {
            quickSort(entrega, 0, entrega.size() - 1);
            return entrega;
        } else {
            switch (tip) {
                case "DF":
                    quickSort(filtroTipo[0], 0, filtroTipo[0].size() - 1);
                    return filtroTipo[0];
                case "FM":
                    quickSort(filtroTipo[1], 0, filtroTipo[1].size() - 1);
                    return filtroTipo[1];
                case "FI":
                    quickSort(filtroTipo[2], 0, filtroTipo[2].size() - 1);
                    return filtroTipo[2];
                case "BO":
                    quickSort(filtroTipo[3], 0, filtroTipo[3].size() - 1);
                    return filtroTipo[3];
                default:
                    throw new AssertionError();
            }
        }
    }

}
