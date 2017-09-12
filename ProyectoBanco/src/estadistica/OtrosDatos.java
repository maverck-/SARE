package estadistica;

import java.io.IOException;
import java.util.ArrayList;
import proyectobanco.IOExcel;
import tipoInv.GestorDatos;

/**
 *
 * @author Maver
 */
public class OtrosDatos extends Calculos {
    
    public OtrosDatos(GestorDatos g) {
        super(g);     
    }

    @Override
    public void informe() throws IOException {
        ArrayList l = Filtro();
        String[][] d = super.getDatos();
        
        d[0][0] = "Esperanza: \t\t\t";
        super.Esperanza();

        d[1][0] = "Varianza: \t\t\t";
        d[4][0] = "Desviación estandar:\t\t";
        d[5][0] = "Coeficiente de variación: \t";
        super.Varianza();

        d[2][0] = "Mediana: \t\t\t";
        super.Mediana();

        d[3][0] = "Moda: \t\t\t\t";
        super.Moda();

        d[6][0] = "Media truncada: \t\t";

        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < d[0].length; j++) {
                try {
                    System.out.print(d[i][j]);
                } catch (NullPointerException e) {
                    System.out.print("No Dato");
                } finally {
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.println("");
        
        //super.getgDatos().getArchivo().SalidaExcel(null,d); //de momento escribe solo esta tabla en el excel!!! - La idea es inicializar la escrituara de excel de otra forma para poder agregar libremente hojas
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
