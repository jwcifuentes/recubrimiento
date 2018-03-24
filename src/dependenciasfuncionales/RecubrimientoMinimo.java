/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependenciasfuncionales;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jcifuentesz
 */
public class RecubrimientoMinimo {
    
    public static ArrayList<String> descomponer(ArrayList<String> dependencias){
        ArrayList<String> retorno= new ArrayList<>();
        System.out.println("tamaño: "+dependencias.size());
        for(String dependencia:dependencias){
            System.out.println("dependencia: "+dependencia);
           String[] data=dependencia.split("->");
//           for(String atributo: atributos){
            if(data[1].contains(".")){
                String relacion = data[0]+":";
                System.out.println("data[1] "+data[1]);
                String[] atributos =data[1].replace(".", ",").split(",");
                System.out.println("tamanio data1 "+atributos.length);
                for(String atrb:atributos){
                    System.out.println("en el for "+data[0]+"->"+atrb+";");
                    relacion+=data[0]+"->"+atrb+";";
                }
                System.out.println("relacion final: "+relacion.substring(0, relacion.length()-1));
                retorno.add(relacion.substring(0, relacion.length()-1));
            }else{
                retorno.add(data[0]+"->"+data[1]);
            }
//           }
        }
        return retorno;
    }
    
    public static ArrayList<String> eliminarExtranios(String[] dependenciasLo){
        Map<String,String> mapCierres= new HashMap<String, String>();
        ArrayList<String> retorno = new ArrayList<>();
        for(String dependencia: dependenciasLo){
            System.out.println("inicio dependencia "+dependencia);
            String izq = dependencia.split("->")[0];
            String dere = dependencia.split("->")[1];
            System.out.println("izquierda: "+izq);
            if(izq.contains(".")){
                String izquierdaFinal="";
                boolean isRedundante=false;
                for(int i=0; i<izq.replace(".", ",").split(",").length;i++){
                    
//                    System.out.println("valor de izquierda en iteracion "+i+": "+izq);
                    String atrb=izq.replace(".", ",").split(",")[i];
                    String prueba=izq.replace(atrb, "");
                    if(prueba.startsWith(".")){
                        prueba=prueba.substring(1, prueba.length());
                    }
                    if(prueba.endsWith(".")){
                        prueba=prueba.substring(0, prueba.length()-1);
                    }
                    if(prueba.contains("..")){
                        prueba=prueba.replace("..", ".");
                    }
                    String cierre="";
                    if(mapCierres.get(prueba)==null){
                        cierre =verificarCierre(prueba, dependenciasLo);
                        System.out.println("cierre "+cierre);
                        if(!cierre.replace(":", "").equals("")){
                            mapCierres.put(prueba, cierre.split(":")[1]);
                        }
                    }else{
                        cierre = mapCierres.get(prueba);
                    }
                    if(cierre.contains(dependencia.split("->")[1])){
                        System.out.println("El atributo "+atrb+" es extraño");
                        izquierdaFinal=izq.replace(atrb, "");
//                        retorno.add(prueba+"->"+dere);
                        
                        isRedundante=true;
                    }
                }
                if(!isRedundante){                        
                    retorno.add(dependencia);
                }
                izquierdaFinal=validarDependencia(izquierdaFinal);
                if(!izquierdaFinal.equals("") && !isRedundante){
                    retorno.add(izquierdaFinal+"->"+dere);
                }else{
                    System.out.println("Se volvio mierda este hijueputa!!!");
                    for(int i=0; i<izq.replace(".", ",").split(",").length;i++){
                        String atrb=izq.replace(".", ",").split(",")[i];
                        String cierre="";
                        if(mapCierres.get(atrb)==null){
                            cierre =verificarCierre(atrb, dependenciasLo);
                            System.out.println("cierre "+cierre);
                            if(!cierre.replace(":", "").equals("")){
                                mapCierres.put(atrb, cierre.split(":")[1]);
                            }
                        }else{
                            cierre = mapCierres.get(atrb);
                        }
                        if(cierre.contains(dependencia.split("->")[1])){
                            retorno.add(atrb+"->"+dere);
                        }
                    }
                }
            }else{
                retorno.add(dependencia);
            }
        }
        
        return retorno;
    }

    private static String verificarCierre(String atrb, String[] dependenciasLo) {
        int i=0;
        
        String retorno=atrb+":"+atrb;
//        System.out.println("dependenciasLo.length "+dependenciasLo.length);
        while(i<dependenciasLo.length){
//            System.out.println("valor i "+i);
            String dependencia= dependenciasLo[i];
//            System.out.println("dependencia: "+dependenciasLo[i]);
            String implicado = dependencia.split("->")[0];
            String implicante = dependencia.split("->")[1];
            boolean encontrado=false;
            
            for(String implcds:implicado.replace(".", ",").split(",")){
                if(atrb.contains(implcds)){
                    encontrado=true;
                }else{
                    encontrado=false;
                    break;
                }
                
            }
            if(encontrado && !atrb.contains(implicante)){
                atrb+="."+implicante;
                retorno+="."+implicante;
                i=0;
            }else{
                i++;
//                System.out.println("No encontro un culo");
            }
        }
//        System.out.println("Fin del while");
        return retorno;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static String validarDependencia(String izq) {
        String retorno="";
        for(int i=0; i<izq.replace(".", ",").split(",").length;i++){
            retorno+=izq.replace(".", ",").split(",")[i].equals("")?"":izq.replace(".", ",").split(",")[i]+".";
        }
        if(!retorno.equals("")){
            retorno=retorno.substring(0, retorno.length()-1);
        }
        return retorno;
        
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static String eliminarRedundancias(String l2){
        
        for(String dependencia:l2.split(",")){
            String evaluacion=l2.replace(dependencia, "");
            if(evaluacion.startsWith(",")){
                evaluacion=evaluacion.substring(1, evaluacion.length());
            }
            if(evaluacion.contains(",,")){
                evaluacion=evaluacion.replace(",,", ",");
            }
            if(evaluacion.endsWith(",")){
                evaluacion=evaluacion.substring(0, evaluacion.length()-1);
            }
            String cierre=verificarCierre(dependencia.split("->")[0], evaluacion.split(","));
            if(cierre.split(":")[1].contains(dependencia.split("->")[1])){
                System.out.println("la dependencia "+dependencia+" es redundante");
                l2=evaluacion;
            }
        }
        return l2;
    }
    
    public static String hallarLLaveCandidata(ArrayList<String> atributos, String l2){
        String retorno="";
        for(String atributo: atributos){
            if(l2.contains(atributo)){
//                atributos.remove(atributo);
                System.out.println("Eliminado por repetido... "+atributo);
            }else{
                retorno+=atributo+",";
            }
        }
        if(!retorno.equals("")){
            return retorno.substring(0, retorno.length()-1);
        }else{
            return retorno;
        }
    }
    
    public static String calcularBCNF(ArrayList<String> atributos, String[] l2){
        Map<String,String> mapCierres= new HashMap<String, String>();
        String cierre ="";
        for(String dependencia: l2){
            System.out.println("inicio dependencia "+dependencia);
            String izq = dependencia.split("->")[0];
            String dere = dependencia.split("->")[1];
            System.out.println("izquierda: "+izq);
            if(izq.contains(".")){
                String izquierdaFinal="";
                boolean isRedundante=false;
                for(int i=0; i<izq.replace(".", ",").split(",").length;i++){
                    
//                    System.out.println("valor de izquierda en iteracion "+i+": "+izq);
                    String atrb=izq.replace(".", ",").split(",")[i];
                    String prueba=izq.replace(atrb, "");
                    if(prueba.startsWith(".")){
                        prueba=prueba.substring(1, prueba.length());
                    }
                    if(prueba.endsWith(".")){
                        prueba=prueba.substring(0, prueba.length()-1);
                    }
                    if(prueba.contains("..")){
                        prueba=prueba.replace("..", ".");
                    }
                    if(mapCierres.get(prueba)==null){
                        cierre =verificarCierre(prueba, l2);
                        System.out.println("cierre "+cierre);
                        if(!cierre.replace(":", "").equals("")){
                            mapCierres.put(prueba, cierre.split(":")[1]);
                        }
                    }else{
                        cierre = mapCierres.get(prueba);
                    }
                }
            }    
        }
        return cierre;        
    }
    
    public static String[] calcular3FN(ArrayList<String> atributos, String[] l2, String llaveCandidata){
        String[] relaciones = new String[l2.length];
        for(int i=0; i<l2.length; i++){
            relaciones[i] = l2[i].replace("->", ",");
            System.out.println("Relacion"+i+"= "+relaciones[i]);
            String llaves = llaveCandidata.replace(",", "");
            if ((relaciones[i].matches(".*["+llaves+"].*"))==false){
                relaciones[i]=llaveCandidata.split(",")[0];
            }
        }
        return relaciones;
    }
    
}
