package estadistica;

import java.util.ArrayList;
import listas.Inversion;
import tipoInv.GestorDatos;

/**
 *
 * @author Maver
 */
public class OtrosDatos extends Calculos {
    private double Varianza;
    private double Esperanza;
    private double Mediana;
    private double Moda;
    private double desviacion;
    private double coefVar;
    private double MediaT;
    private String[][] datos;
    private String[][] tabla;
    private int k;
    
    public OtrosDatos(GestorDatos g) {
        super(g);     
        datos = new String[7][2];        
        if ((int) Math.sqrt(Filtro().size()) > 15) {
            k = 15;
        } else {
            k = (int) Math.sqrt(Filtro().size());
        }
        tabla = new String[k + 1][9];
    }

    @Override
    public void informe() {
        ArrayList lista = super.getgDatos().Filtro();
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

    protected int Get(ArrayList e, int i) {
        return ((Inversion) e.get(i)).getMontoI();
    }    

    @Override
    public ArrayList Filtro() {
        return super.getgDatos().Filtro();
    }
}
