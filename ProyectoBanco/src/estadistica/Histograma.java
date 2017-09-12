/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estadistica;

import java.util.ArrayList;
import listas.Inversion;
import tipoInv.GestorDatos;

/**
 *
 * @author Maver
 */
public final class Histograma extends Calculos {
    
    private int k;
    private String[][] histograma;
    private String[][] datos;
    private String[][] tabla;    

    public Histograma(GestorDatos g) {
        super(g);
        datos = new String[7][2];
        if ((int) Math.sqrt(Filtro().size()) > 15) {
            k = 15;
        } else {
            k = (int) Math.sqrt(Filtro().size());
        }
        histograma = new String[k][Filtro().size()];
        tabla = new String[k + 1][9];
        
    }
    
    @Override
    public void informe() {
        ArrayList n = Filtro();

        tabla[0][0] = "N° clase";
        tabla[0][1] = "Límites intervalos \t";
        tabla[0][2] = "Marca de clase";
        tabla[0][3] = "N° observaciones \t";
        tabla[0][4] = "Frecuencia relativa \t";
        tabla[0][5] = "Porcentaje relativo \t";
        tabla[0][6] = "N° Obs. acumuladas \t";
        tabla[0][7] = "Frec. relativa acumulada";
        tabla[0][8] = "Porc. relativo acumulado";

        int mayor = Get(n, n.size() - 1);
        int menor = Get(n, 0);
        int largo = mayor - menor;
        int rango = largo / k;
        double count = 0;
        for (int c = 0; c < histograma.length; c++) { //k=15
            double men = menor + (rango * c);
            double may = menor + (rango * (c + 1));
            double parcial = 0;
            boolean seguir = true;
            if (c == k - 1) {
                may += k;
            }
            tabla[c + 1][0] = c + 1 + "\t";
            tabla[c + 1][1] = "[" + (int) men + "-" + (int) may + "[ \t";
            tabla[c + 1][2] = (int) (men + may) / 2 + "\t";
            for (int i = (int) count; i < n.size() && seguir; i++) {
                if (men <= Get(n, i) && Get(n, i) < may) {
                    parcial++;
                    count++;
                } else {
                    seguir = false;
                }
            }
            tabla[c + 1][3] = (int) parcial + "\t\t\t";
            tabla[c + 1][4] = parcial / n.size() + "\t";
            tabla[c + 1][5] = parcial / n.size() * 100 + "\t";
            tabla[c + 1][6] = (int) count + "\t\t\t";
            tabla[c + 1][7] = count / n.size() + "\t";
            tabla[c + 1][8] = count / n.size() * 100 + "\t";
        }
        
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                try {
                    System.out.print(tabla[i][j]);
                } catch (NullPointerException e) {
                    System.out.print("No Dato");
                } finally {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public ArrayList Filtro() {
        return super.getgDatos().Filtro();
    }

}
