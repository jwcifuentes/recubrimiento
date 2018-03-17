/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependenciasfuncionales;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcifuentesz
 */
public class DependenciasFuncionales {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File archivo = new File("F:\\MAESTRIA\\pruebaCarga.txt");
        Map<String, String> mapOpciones = leertxt(archivo);
        ArrayList<String> atributos = obtenerElementosMap(mapOpciones, "T");
        ArrayList<String> relaciones = obtenerElementosMap(mapOpciones, "L");
        ArrayList<String> descomp = RecubrimientoMinimo.descomponer(relaciones);
        String l0="";
        ArrayList<String> pimeraIteracionL0 = new ArrayList<>();
        for(String rel: descomp){
            if(rel.contains(":")){
                String spl=rel.split(":")[1];
                l0+=spl.replace(";", ",")+",";
                
            }else{
                l0+=rel+",";
            }
        }
        l0=l0.substring(0, l0.length()-1);
        System.out.println("Valor de L0= "+l0);
        ArrayList<String> l1 = RecubrimientoMinimo.eliminarExtranios(l0.split(","));
        String valorl1="";
        for(String dep:l1){
            valorl1+=dep+",";
        }
        valorl1=valorl1.substring(0, valorl1.length()-1);
        System.out.println("Valor de l1: "+valorl1);
        String l2 = RecubrimientoMinimo.eliminarRedundancias(valorl1);
        System.out.println("Valor de l2: "+l2);
        String w = "";
        for(String relacion:l2.split(",")){
            w+=relacion.split("->")[1]+",";
        }
        w=w.substring(0, w.length()-1);
        String llaveCandidata=RecubrimientoMinimo.hallarLLaveCandidata(atributos, w);
        System.out.println("Llave candidata: "+llaveCandidata);
//        leerOpciones(atributos);
//        leerOpciones(relaciones);
    }

    public static Map<String, String> leertxt(File archivo) {
        Map<String, String> retorno = new HashMap<>();
        try{
        FileReader fr = new FileReader (archivo);
        BufferedReader br = new BufferedReader(fr);
        String linea;
         while((linea=br.readLine())!=null){
             System.out.println("linea: "+linea);
             String[] datLine = linea.split("=");
             retorno.put(datLine[0], datLine[1].replace("{", "").replace("}", ""));
         }
         return retorno;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DependenciasFuncionales.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DependenciasFuncionales.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static ArrayList<String> obtenerElementosMap(Map<String, String> mapOpciones, String key) {
        String data = mapOpciones.get(key);
        ArrayList<String> elementos = new ArrayList<>();
        for(String elemento:data.split(",")){
            elementos.add(elemento);
        }
        return elementos;
    }

    private static void leerOpciones(ArrayList<String> atributos) {
        for(String dato:atributos){
            System.out.println("elemento: "+dato);
        }
    }
    
}
