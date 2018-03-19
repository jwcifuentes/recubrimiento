/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dependenciasfuncionales;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jcalle
 */
public class RecubrimientoMinimoTest1 {
    
    public RecubrimientoMinimoTest1() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of descomponer method, of class RecubrimientoMinimo.
     */
    @Test
    public void testDescomponer() {
        System.out.println("descomponer");
        ArrayList<String> dependencias = new ArrayList<String>();
        dependencias.add("A->B");
        dependencias.add("A.B->E");
        dependencias.add("A.D->C");
        dependencias.add("B.C->E");
        dependencias.add("F->H");
        dependencias.add("A->I");
        dependencias.add("A->J");
        dependencias.add("F.G->H");
        dependencias.add("E.F->H");
        dependencias.add("A.F->G");
        dependencias.add("A.F->H");
        dependencias.add("G.F->H");        
        dependencias.add("E.G->F");
        dependencias.add("A.G->F");
        dependencias.add("C.D.E->J");
        dependencias.add("D.E.F->I");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("A->B");
        expResult.add("B->C");
        expResult.add("B->D");
        expResult.add("A->E");
        expResult.add("F->H");
        expResult.add("A->I");
        expResult.add("A->J");
        expResult.add("F.G->H");
        expResult.add("E.F->H");
        expResult.add("A.F->G");
        expResult.add("A.F->H");
        expResult.add("E.G->F");
        expResult.add("A.G->F");
        expResult.add("C.D.E->J");
        expResult.add("D.E.F->I");
        ArrayList<String> result = RecubrimientoMinimo.descomponer(dependencias);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarExtranios method, of class RecubrimientoMinimo.
     */
    @Test
    public void testEliminarExtranios() {
        System.out.println("eliminarExtranios");
        String[] dependenciasLo = {"A->B","B->C","B->D","A->E","E.F->G","F->H","A->I","A->J","F.G->H","A.F->G","E.F->H","E.G->F","A.G->F","C.D.E->J","D.E.F->I"};
        ArrayList<String> expResult = new  ArrayList<String>();
        expResult.add("A->B");
        expResult.add("B->C");
        expResult.add("B->D");
        expResult.add("A->E");
        expResult.add("F->H");
        expResult.add("A->I");
        expResult.add("A->J");
        expResult.add("E.F->G");
        expResult.add("A.F->G");
        expResult.add("E.G->F");
        expResult.add("A.G->F");
        expResult.add("C.D.E->J");
        expResult.add("D.E.F->I");
        ArrayList<String> result = RecubrimientoMinimo.eliminarExtranios(dependenciasLo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarRedundancias method, of class RecubrimientoMinimo.
     */
    @Test
    public void testEliminarRedundancias() {
        System.out.println("eliminarRedundancias");
        String l1 = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,E.F->G,A.F->G,E.G->F,A.G->F,C.D.E->J,D.E.F->I";
        String expResult = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,E.F->G,E->F,C.D.E->J,D.E.F->I";
        String result = RecubrimientoMinimo.eliminarRedundancias(l1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
    /**
     * Test of hallarLLaveCandidata method, of class RecubrimientoMinimo.
     */
    @Test
    public void testHallarLLaveCandidata() {
        System.out.println("hallarLLaveCandidata");
        ArrayList<String> atributos = new ArrayList<String>();
        atributos.add("A");
        atributos.add("B");
        atributos.add("C");
        atributos.add("D");
        atributos.add("E");
        atributos.add("F");
        atributos.add("G");
        atributos.add("H");
        atributos.add("I");
        atributos.add("J");
        String l2 = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,E.F->G,E->F,C.D.E->J,D.E.F->I";
        String expResult = "A";
        String result = RecubrimientoMinimo.hallarLLaveCandidata(atributos, l2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
