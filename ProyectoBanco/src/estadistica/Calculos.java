
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
        Moda = 0;

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
    
    protected double getVarianza() {
        return Varianza;
    }

    protected double getEsperanza() {
        return Esperanza;
    }

    protected double getMediana() {
        return Mediana;
    }

    protected double getModa() {
        return Moda;
    }

    protected double getDesviacion() {
        return desviacion;
    }

    protected double getCoefVar() {
        return coefVar;
    }

    protected double getMediaT() {
        return MediaT;
    }
    /*
    protected int Get(ArrayList e, int i) {
        return ((Inversion) e.get(i)).getMontoI();
    }
    
    public Calculos(GestorDatos g) {
        datos = new String[7][2];
        if ((int) Math.sqrt(g.FiltrarFecha().size()) > 15) {
            k = 15;
        } else {
            k = (int) Math.sqrt(g.FiltrarFecha().size());
        }
        histograma = new String[k][g.FiltrarFecha().size()];
        tabla = new String[k + 1][9];
    }

    public String[][] otrosDatos(ArrayList lista) {
        datos[0][0] = "Esperanza: \t\t\t";
        Esperanza(lista);

        datos[1][0] = "Varianza: \t\t\t";
        datos[4][0] = "Desviación estandar:\t\t";
        datos[5][0] = "Coeficiente de variación: \t";
        Varianza(lista);

        datos[2][0] = "Mediana: \t\t\t";
        Mediana(lista);

        datos[3][0] = "Moda: \t\t\t\t";
        Moda(lista);

        datos[6][0] = "Media truncada: \t\t";

        return datos;
    }

    private void Esperanza(ArrayList lista) {
        double suma = 0;
        for (int i = 0; i < lista.size(); i++) {
            suma += Get(lista, i);
        }
        Esperanza = suma / lista.size();
        datos[0][1] = Esperanza + "";
    }

    private void Moda(ArrayList lista) {

        Moda = 0;

        datos[3][1] = Moda + "";
        MediaT = 0;
        datos[6][1] = MediaT + "";
    }

    private void Mediana(ArrayList lista) {
        if (lista.size() % 2 == 0) {
            Mediana = Get(lista, lista.size() / 2) + Get(lista, lista.size() / 2 - 1);
            datos[2][1] = Mediana + "";
            return;
        }
        Mediana = Get(lista, (int) lista.size() / 2);
        datos[2][1] = Mediana + "";
    }

    private void Varianza(ArrayList lista) {
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
    }

    public String[][] tablaDeFrecuencia(ArrayList lista) {
        tabla[0][0] = "N° clase";
        tabla[0][1] = "Límites intervalos \t";
        tabla[0][2] = "Marca de clase";
        tabla[0][3] = "N° observaciones \t";
        tabla[0][4] = "Frecuencia relativa \t";
        tabla[0][5] = "Porcentaje relativo \t";
        tabla[0][6] = "N° Obs. acumuladas \t";
        tabla[0][7] = "Frec. relativa acumulada";
        tabla[0][8] = "Porc. relativo acumulado";

        int mayor = Get(lista, lista.size() - 1);
        int menor = Get(lista, 0);
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
            for (int i = (int) count; i < lista.size() && seguir; i++) {
                if (men <= Get(lista, i) && Get(lista, i) < may) {
                    parcial++;
                    count++;
                } else {
                    seguir = false;
                }
            }
            tabla[c + 1][3] = (int) parcial + "\t\t\t";
            tabla[c + 1][4] = parcial / lista.size() + "\t";
            tabla[c + 1][5] = parcial / lista.size() * 100 + "\t";
            tabla[c + 1][6] = (int) count + "\t\t\t";
            tabla[c + 1][7] = count / lista.size() + "\t";
            tabla[c + 1][8] = count / lista.size() * 100 + "\t";
        }
        return tabla;
    }*/

}
