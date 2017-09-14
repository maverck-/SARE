package proyectobanco;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import listas.Cliente;
import listas.Inversion;
import listas.Lista;
import listas.NoDato;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Eduardo
 */
public class EstrategiaX extends IOEstrategia{
    
    public EstrategiaX(String nom) throws NoDato,IOException,NumberFormatException{
        super(nom);
    }
    
    @Override
    public Lista Lectura() throws IOException, NoDato, NumberFormatException {
        FileInputStream file = new FileInputStream(nomArchivo);
        XSSFWorkbook libro = new XSSFWorkbook(file);
        XSSFSheet hoja = libro.getSheetAt(0);
        Iterator rows = hoja.rowIterator();
        int j = 0;
        while (rows.hasNext()) {
            XSSFRow row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            List<String> df = new ArrayList();
            for (int i = 0; i < 10; i++) {
                XSSFCell cell = null;
                if (cells.hasNext()) {
                    cell = (XSSFCell) cells.next();
                }
                try {
                    df.add(agregarCelda(cell));
                } catch (NoDato e) {
                    df.add(null);
                }

            }
            if (j != 0) {
                Cliente c = new Cliente(df.get(1), df.get(2));
                if (!clientes.contains(c)) {
                    clientes.add(c);
                }
                inversiones.add(new Inversion(clientes.indexOf(c), (int) Double.parseDouble(df.get(0)), df.get(3), (int) Double.parseDouble(df.get(4)), Double.parseDouble(df.get(5)), df.get(7), df.get(8), df.get(9)));
            }
            j++;
        }
        if (file != null) {
            file.close();
        }
        return inversiones;
    }
    
    private String agregarCelda(XSSFCell cell) throws NoDato {
        if (cell == null) {
            throw new NoDato();
        }
        return cell.toString();
    }
    
    public void Escritura(){
        
    }
    
}

