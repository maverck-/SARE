
package proyectobanco;

/**
 *
 * @author BSOD
 */

import java.util.Date;

public class Fecha {
    private String fec_inv;
    private String fec_fin;
    private String fec_cier;    

    public Fecha(String fechaI,String fechaF,String fechaC){
        fec_inv=fechaI;
        fec_fin=fechaF;
        fec_cier=fechaC;
    }
    
    public Date getCierre(){
        return ConvertirFecha(fec_cier);
    }
    
    public Date getInicio(){
        return ConvertirFecha(fec_inv);
    }
    
    public Date getFinal(){
        return ConvertirFecha(fec_fin);
    }
    
    
    private Date ConvertirFecha(String fecha){
        if(fecha==null)
            return null;
        String[] nuevaF=fecha.split("-");     
        switch (nuevaF[1]){
            //español
            case "ene": nuevaF[1]=0+"";break;
            case "feb": nuevaF[1]=1+"";break;
            case "mar": nuevaF[1]=2+"";break;
            case "abr": nuevaF[1]=3+"";break;
            case "may": nuevaF[1]=4+"";break;    
            case "jun": nuevaF[1]=5+"";break;
            case "jul": nuevaF[1]=6+"";break;
            case "ago": nuevaF[1]=7+"";break;
            case "sep": nuevaF[1]=8+"";break;
            case "oct": nuevaF[1]=9+"";break;
            case "nov": nuevaF[1]=10+"";break;
            case "dic": nuevaF[1]=11+"";break;
            //inglés
            case "jan": nuevaF[1]=0+"";break;
            case "Jan": nuevaF[1]=0+"";break;
            case "Feb": nuevaF[1]=1+"";break;
            case "Mar": nuevaF[1]=2+"";break;
            case "Apr": nuevaF[1]=3+"";break;
            case "May": nuevaF[1]=4+"";break;    
            case "Jun": nuevaF[1]=5+"";break;
            case "Jul": nuevaF[1]=6+"";break;
            case "Agu": nuevaF[1]=7+"";break;
            case "Aug": nuevaF[1]=7+"";break;
            case "Sep": nuevaF[1]=8+"";break;
            case "Oct": nuevaF[1]=9+"";break;
            case "Nov": nuevaF[1]=10+"";break;
            case "Dic": nuevaF[1]=11+"";break;
            case "Dec": nuevaF[1]=11+"";break;
        }
        Date nfecha=new Date(Integer.parseInt(nuevaF[2])-1900,Integer.parseInt(nuevaF[1]),Integer.parseInt(nuevaF[0]));
        return nfecha;
    }        
 } 
