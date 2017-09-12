
package estadistica;

/**
 *
 * @author Eduardo
 */
import listas.*;
import java.util.*;
import tipoInv.*;

public abstract class Calculos extends GestorDatos {

    private GestorDatos gDatos;

    private double Varianza;
    private double Esperanza;
    private double Mediana;
    private double Moda;
    private double desviacion;
    private double coefVar;
    private double MediaT;
    private int k;
    private String[][] histograma;
    private String[][] datos;
    private String[][] tabla;
    private ArrayList lista;

    public Calculos(GestorDatos g) {
        gDatos = g;
        datos = new String[7][2];
        if ((int) Math.sqrt(gDatos.Filtro().size()) > 15) {
            k = 15;
        } else {
            k = (int) Math.sqrt(gDatos.Filtro().size());
        }
        histograma = new String[k][gDatos.Filtro().size()];
        tabla = new String[k + 1][9];
        lista = gDatos.Filtro();
    }
    
    protected void Esperanza() {
        double suma = 0;
        for (int i = 0; i < lista.size(); i++) {
            suma += Get(lista, i);
        }
        Esperanza = suma / lista.size();
        datos[0][1] = Esperanza + "";
    }

    protected void Moda() {
        Moda = (double)gDatos.Filtro().size();

        datos[3][1] = Moda + "";
        MediaT = 0;
        datos[6][1] = MediaT + "";
    }

    protected void Mediana() {
        if (lista.size() % 2 == 0) {
            Mediana = Get(lista, lista.size() / 2) + Get(lista, lista.size() / 2 - 1);
            datos[2][1] = Mediana + "";
            return;
        }
        Mediana = Get(lista, (int) lista.size() / 2);
        datos[2][1] = Mediana + "";
    }

    protected double Varianza() {
        double preVarianza = 0.0;
        for (int x = 0; x < lista.size(); x++) {
            preVarianza += (Get(lista, x) - Esperanza) * (Get(lista, x) - Esperanza);
        }
        Varianza = preVarianza / (lista.size());
        datos[1][1] = Varianza + "";
        desviacion = Math.sqrt(Varianza);
        datos[4][1] = desviacion + "";
        coefVar = desviacion / Esperanza;
        datos[5][1] = coefVar + "";
        return Varianza;
    }

    protected GestorDatos getgDatos() {
        return gDatos;
    }

    protected int getK() {
        return k;
    }

    protected String[][] getHistograma() {
        return histograma;
    }

    protected String[][] getDatos() {
        return datos;
    }

    protected String[][] getTabla() {
        return tabla;
    }

}
