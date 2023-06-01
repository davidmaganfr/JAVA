package com.cloudftic;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * Para controlar el dato introducido por el usuario podemos sustituir
 * System.in por un objeto cuyo contenido controlemos.
 */
public class Ejercicio03Test {
    private static InputStream inOriginal;

    @BeforeAll
    static void beforeAll() {
        inOriginal = System.in;
    }

    @AfterAll
    static void afeterAll() {
        System.setIn(inOriginal); // se restaura el teclado
    }

    @ParameterizedTest
    @MethodSource("paramsToTestSolicitarEdadValid")
    void testSolicitarEdadValid(Integer edadMinima, Integer edadMaxima, String edadDada) {
        // Provocamos que se lea la edad dada
        System.setIn(new ByteArrayInputStream(edadDada.getBytes()));
        // Si lanza excepcion falla la prueba, sino retorna la edad
        int edad = assertDoesNotThrow(() -> Ejercicio03.solicitarEdad(edadMinima, edadMaxima));
        // Comprobamos que la edad esta dentro del rango
        assertTrue(
                edad >= edadMinima && edad <= edadMaxima,
                String.format("Falla: %d no está entre [%d, %d]", edad, edadMaxima, edadMaxima));
    }

    @ParameterizedTest
    @MethodSource("paramsToTestSolicitarEdadNovalid")
    void testSolicitarEdadNovalid(Integer edadMinima, Integer edadMaxima, String edadDada) {
        // Provocamos que se lea la edad dada
        System.setIn(new ByteArrayInputStream(edadDada.getBytes()));
        // Comprobamos que lance excepcion
        assertThrows(EdadFueraRango.class, () -> Ejercicio03.solicitarEdad(edadMinima, edadMaxima));
    }

    static Stream<Arguments> paramsToTestSolicitarEdadValid() {
        return Stream.of( // {edad_minima, edad_maxima, edad_para_leer}
                Arguments.of(1, 60, "1"),
                Arguments.of(1, 60, "30"),
                Arguments.of(1, 60, "60"));
    }

    static Stream<Arguments> paramsToTestSolicitarEdadNovalid() {
        return Stream.of( // {edad_minima, edad_maxima, edad_para_leer}
                Arguments.of(1, 60, "0"),
                Arguments.of(1, 60, "61"),
                Arguments.of(1, 60, "diez"));
    }
}
