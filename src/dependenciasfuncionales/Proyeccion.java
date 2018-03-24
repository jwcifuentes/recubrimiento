/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependenciasfuncionales;

import java.util.ArrayList;

/**
 *
 * @author jcifuentesz
 */
public class Proyeccion {
    
    public static ArrayList<String> Proyeccion(String l2, ArrayList<String> atributos) {
		String[] cubMin = l2.split(",");
		String[] g = cubMin;
                ArrayList<String> g1 = new ArrayList<>();
		ArrayList<String> w = new ArrayList<>();
                ArrayList<String> arreglo = new ArrayList<>();
		w.addAll(atributos);
                //De donde sale Atr???
		//w.removeAll(Atr);
		System.out.println("Conjunto G " + w);
		for (String a : w) {
			ArrayList<String> ats = new ArrayList<>();
			ats.add(a);
			System.out.println("Atributo A " + ats);
			ArrayList<String> Hd = new ArrayList<>();
			ArrayList<String> Hi = new ArrayList<>();
			ArrayList<String> toAdd = new ArrayList<>();
			for (String y : g) {
				if (y.split("->")[0].contains(a)) {
					Hi.add(y);
					System.out.println("Dependencia IZQ " + Hi);
				}
				if (y.split("->")[1].contains(a) && !y.split("->")[1].contains(".")) {
					Hd.add(y);
					System.out.println("Dependencia DER " + Hd);
				}

			}
			if (!Hd.isEmpty()) {
				for (String Der : Hd) {
					if (!Hi.isEmpty()) {
						for (String Izq : Hi) {
							if (!Izq.split("->")[1].equals(ats)) {
								ArrayList<String> IzqN = new ArrayList<>();
								IzqN.add(Izq.split("->")[0]);
								IzqN.remove(a);
								IzqN.add(Der.split("->")[0]);
								String a1 = IzqN+"->"+Izq.split("->")[1];
								if (IzqN.equals(Izq.split("->")[1])) {
									System.out.println(IzqN + " Es igual que " + Izq.split("->")[1]);
								} else {
									toAdd.add(a1);
								}
							}
						}
					}
				}
			}
                    arreglo = removeFromArray(g,Hi);
                    arreglo=removeFromArray((String[]) arreglo.toArray(),Hd);
                    
                    arreglo.addAll(toAdd);
                    System.out.println("Nuevo G " + arreglo);
		}

		return arreglo;

	}

    private static ArrayList<String> removeFromArray(String[] g, ArrayList<String> Hi) {
        ArrayList<String> gEval = new ArrayList<>();
        for(String g1:g){
            boolean existe=false;
            for(String hunit:Hi){
                if(hunit.equals(g1)){
                    existe=true;
                }
            }
            if(!existe){
                gEval.add(g1);
            }
        }
        return gEval;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
