package estadistica;

import java.io.IOException;
import java.util.ArrayList;
import proyectobanco.IOExcel;
import tipoInv.GestorDatos;

/**
 *
 * @author Maver
 */
public class Histograma extends Calculos {  

    public Histograma(GestorDatos g) {
        super(g);   
    }
    
    @Override
    public void informe(){
        ArrayList n = Filtro();
        String[][] tabla = super.getTabla();
        String[][] histograma = super.getHistograma();
        int k = super.getK();

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
        //super.getgDatos().getArchivo().SalidaExcel(tabla,null);
    }

    @Override
    public ArrayList Filtro() {
        return super.getgDatos().Filtro();
    }

    @Override
    public IOExcel getArchivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
