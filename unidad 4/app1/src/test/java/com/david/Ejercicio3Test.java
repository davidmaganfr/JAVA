package com.david;

import java.io.ByteArrayInputStream;
import java.util.function.Supplier;

import org.junit.Test;

public class Ejercicio3Test {
    
    @Test
    public void testSolicitarEdad() {
        int edadMinima = 4;
        int edadMaxima = 15;
        Supplier<Integer> edad = () -> 10; //teclado virtual para variable edad
        Exception exc = null;
        
        try{
            Ejercicio3.solicitarEdad(edadMinima, edadMaxima, edad);
        }catch(Exception e){
            exc = e;
        }

        assert exc == null;
    }
}
