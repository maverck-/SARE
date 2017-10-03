package listas;

/**
 *
 * @author BSOD
 */
import java.util.Date;
import proyectobanco.Fecha;

public class Inversion {

    private int id_inv;
    private String tipo_inv;
    private int mon_inv;
    private double tasa_int;
    private double mon_rec;
    private Fecha fec;
    private boolean inv_cerrada;

    public Inversion( int id, String tipo, int montoInicial, double tasa, String fi, String ff, String fc) {
        id_inv = id;
        tipo_inv = tipo;
        mon_inv = montoInicial;
        tasa_int = tasa;
        mon_rec = montoInicial * (1 + tasa);
        fec = new Fecha(fi, ff, fc);
        inv_cerrada = false;
    }

    private boolean InversionCerrada() {
        if (fec.getCierre() != null) {
            inv_cerrada = true;
        }
        return inv_cerrada;
    }

    public Date getCierre() {
        if (InversionCerrada()) {
            return fec.getCierre();
        }
        return null;
    }

    public int getMontoI() {
        return mon_inv;
    }

    public Fecha getFechas() {
        return fec;
    }

    public String getTipo() {
        return tipo_inv;
    }

}
