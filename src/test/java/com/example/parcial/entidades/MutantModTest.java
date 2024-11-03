package com.example.parcial.entidades;

import com.example.parcial.Funcion;
import com.example.parcial.services.PersonaServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.Instant;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MutantModTest {

    //dna para realizar tests unitarios
    List<String> dna_1 = Arrays.asList("AAAA","CCCC","TCAG","GGTC");    //true - horizontal
    List<String> dna_2 = Arrays.asList("AAAT","AACC","AAAC","CGGG");    //false
    List<String> dna_3 = Arrays.asList("TGAC","AGCC","TGAC","GGTC");    //true - vertical
    List<String> dna_4 = Arrays.asList("AAAA","AAAA","AAAA","AAAA");    //false
    List<String> dna_5 = Arrays.asList("TGAC","ATCC","TAAG","GGTC");    //false
    List<String> dna_6 = Arrays.asList(
            "TCGGGTGAT",
            "TGATCCTTT",
            "TACGAGTGA",
            "AAATGTACG",
            "ACGAGTGCT",
            "AGACACATG",
            "GAATTCCAA",
            "ACTACGACC",
            "TGAGTATCC"
    );      //true
    List<String> dna_7 = Arrays.asList(
            "TTTTTTTTT",
            "TTTTTTTTT",
            "TTTTTTTTT",
            "TTTTTTTTT",
            "CCGACCGAT",
            "GGCACTCCA",
            "AGGACACTA",
            "CAAAGGCAT",
            "GCAGTCCCC"
    );      //true

    //====== TEST PARA EL ALGORITMO MODIFICADO ======//
    private static Instant startTime;

    @BeforeAll
    public static void init() {
        startTime = Instant.now();
    }

    @Test
    public void testMod1() {
        System.out.println("dna_1 - testMod1");
        assertTrue(Funcion.isMutant(dna_1));
    }
    @Test
    public void testMod2() {
        System.out.println("dna_2 - testMod2");
        assertFalse(Funcion.isMutant(dna_2));
    }
    @Test
    public void testMod3() {
        System.out.println("dna_3 - testMod3");
        assertTrue(Funcion.isMutant(dna_3));
    }
    @Test
    public void testMod4() {
        System.out.println("dna_4 - testMod4");
        assertFalse(Funcion.isMutant(dna_4));
    }
    @Test
    public void testMod5() {
        System.out.println("dna_5 - testMod5");
        assertFalse(Funcion.isMutant(dna_5));
    }
    @Test
    public void testMod6() {
        System.out.println("dna_6 - testMod6");
        assertTrue(Funcion.isMutant(dna_6));
    }
    @Test
    public void testMod7() {
        System.out.println("dna_7 - testMod7");
        assertTrue(Funcion.isMutant(dna_7));
    }

    @AfterEach
    public void printLineMod() {
        System.out.println("-------------------");
    }

    @AfterAll
    public static void printTime() {
        Instant endTime = Instant.now();
        Duration duration = Duration.between(startTime, endTime);
        System.out.println("Tiempo total de ejecución: " + duration.toMillis() + " ms");
    }
}
