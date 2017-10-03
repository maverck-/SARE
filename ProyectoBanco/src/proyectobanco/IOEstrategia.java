package proyectobanco;

import listas.NoDato;
import listas.Lista;
import listas.Inversion;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Date;

/**
 *
 * @author BSOD
 */

public class IOEstrategia {

    private String nomArchivo;
    private Lista inversiones;

    public IOEstrategia(String nombre) throws NoDato, IOException, NumberFormatException {
        nomArchivo = nombre;
        inversiones = new Lista();
    }

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
                inversiones.add(new Inversion( (int) Double.parseDouble(df.get(0)), df.get(3), (int) Double.parseDouble(df.get(4)), Double.parseDouble(df.get(5)), df.get(7), df.get(8), df.get(9)));
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

    public void Escritura(String[][] p, String[][] q) {
        XSSFWorkbook libro = new XSSFWorkbook();
        XSSFSheet hoja = libro.createSheet("Tabla de frecuencias");
        for (int i = 0; i < p.length; i++) {
            XSSFRow fila = hoja.createRow(i);
            for (int j = 0; j < p[0].length; j++) {
                XSSFCell celda = fila.createCell(j);
                if (i == 0 || j == 1) {
                    celda.setCellValue(p[i][j]);
                }
                if (i != 0 && (j == 0 || j == 2 || j == 3 || j == 6)) {
                    celda.setCellValue(Integer.parseInt(p[i][j].trim()));
                }
                if (i != 0 && (j == 4 || j == 5 || j == 7 || j == 8)) {
                    celda.setCellValue(Double.parseDouble(p[i][j].trim()));
                }
                hoja.autoSizeColumn(j);
            }

        }
        XSSFSheet hoja1 = libro.createSheet("Medidas de tendencia central");
        for (int i = 0; i < q.length; i++) {
            XSSFRow fila = hoja1.createRow(i);
            for (int j = 0; j < q[0].length; j++) {
                XSSFCell celda = fila.createCell(j);
                if (j == 0) {
                    celda.setCellValue(q[i][j]);
                }
                if (j == 1) {
                    celda.setCellValue(Double.parseDouble(q[i][j].trim()));
                }
                hoja1.autoSizeColumn(j);
            }
        }
        java.util.Date fecha = new Date();
        String[] fechaV = fecha.toString().split(" ");
        String[] fechaVH = fechaV[3].split(":");
        String nfecha = fechaV[2] + "_" + fechaV[1] + "_" + fechaV[5] + "_" + fechaVH[0] + "_" + fechaVH[1] + "_" + fechaVH[2];
        try {
            FileOutputStream elFichero = new FileOutputStream("Informe_Estadístico_" + nfecha + ".xlsx");
            libro.write(elFichero);
            elFichero.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
