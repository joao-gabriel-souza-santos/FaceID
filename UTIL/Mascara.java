package UTIL;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class Mascara {

    public Mascara(){
        
    }

    public static JFormattedTextField mascaraCpf(JFormattedTextField campo){
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("###.###.###-##");
            mask.install(campo);
        } catch (ParseException e) {

            e.printStackTrace();
        }
       return campo; 
    }

    public static JFormattedTextField mascaraTelefone(JFormattedTextField campo){
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("(##) # ####-####");
            mask.install(campo);
        } catch (ParseException e) {
           
            e.printStackTrace();
        }
       return campo; 
    }

    public static JFormattedTextField mascaraMatricula(JFormattedTextField campo){
        MaskFormatter mask;
        try {
            mask = new MaskFormatter("########-##");
            mask.install(campo);
        } catch (ParseException e) {

            e.printStackTrace();
        }
       return campo; 
    }
}
