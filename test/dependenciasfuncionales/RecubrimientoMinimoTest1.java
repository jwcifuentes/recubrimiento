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
        dependencias.add("B->CD");
        dependencias.add("A->E");
        dependencias.add("EF->G");
        dependencias.add("F->H");
        dependencias.add("A->I");
        dependencias.add("A->J");
        dependencias.add("FG->H");
        dependencias.add("EF->H");
        dependencias.add("AF->G");
        dependencias.add("AF->H");
        dependencias.add("GF->H");        
        dependencias.add("EG->F");
        dependencias.add("AG->F");
        dependencias.add("CDE->J");
        dependencias.add("DEF->I");
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("A->B");
        expResult.add("B->C");
        expResult.add("B->D");
        expResult.add("A->E");
        expResult.add("F->H");
        expResult.add("A->I");
        expResult.add("A->J");
        expResult.add("FG->H");
        expResult.add("EF->H");
        expResult.add("AF->G");
        expResult.add("AF->H");
        expResult.add("EG->F");
        expResult.add("AG->F");
        expResult.add("CDE->J");
        expResult.add("DEF->I");
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
        String[] dependenciasLo = {"A->B","B->C","B->D","A->E","EF->G","F->H","A->I","A->J","FG->H","AF->G","EF->H","EG->F","AG->F","CDE->J","DEF->I"};
        ArrayList<String> expResult = new  ArrayList<String>();
        expResult.add("A->B");
        expResult.add("B->C");
        expResult.add("B->D");
        expResult.add("A->E");
        expResult.add("F->H");
        expResult.add("A->I");
        expResult.add("A->J");
        expResult.add("EF->G");
        expResult.add("AF->G");
        expResult.add("EG->F");
        expResult.add("AG->F");
        expResult.add("CDE->J");
        expResult.add("DEF->I");
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
        String l1 = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,EF->G,AF->G,EG->F,AG->F,CDE->J,DEF->I";
        String expResult = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,EF->G,E->F,CDE->J,DEF->I";
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
        String l2 = "A->B,B->C,B->D,A->E,F->H,A->I,A->J,EF->G,E->F,CDE->J,DEF->I";
        String expResult = "A";
        String result = RecubrimientoMinimo.hallarLLaveCandidata(atributos, l2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
