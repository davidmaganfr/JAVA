package com.cloudftic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;


/*
Crea una anotación @ValoresValidos que permita especificar un array de valores válidos 
para una variable. Permite que la anotacíon pueda ser aplicada sobre campos de una clase.

Crea el siguiente  método:

public static void changeValue(Object objeto, String nombreCampos, Object nuevoValor) {
}

Este método se utilizará para cambiar el valor de un campo de un objeto. Si el campo no 
está anotado con @ValoresValidos se modificará su valor. Si está anotado se debe comprobar 
previamente si el nuevo valor es válido. Si no es válido el método debe lanzar una excepción.
 */

public class Ejercicio06 {
    static class Prueba {
        @ValoresValidos({ "1", "2", "3", "4" })
        public int x;
        @ValoresValidos({ "1", "2", "3", "4" })
        public String s;
    }

    public static void main(String[] args) {
        var p = new Prueba();
        changeValue(p, "x", 3);
        System.out.println(p.x);
        changeValue(p, "s", "4");
        System.out.println(p.s);
    }

    public static void changeValue(Object objeto, String nombreCampo, Object nuevoValor) {
        try {
            //mira si tiene el campo metido en el parametro y devuelve su nombre
            Field campoRef = objeto.getClass().getDeclaredField(nombreCampo); 
            //mira si el campo tiene la anotacion ValoresValidos y devuelve su nombre y el array
            var validosRef = campoRef.getDeclaredAnnotation(ValoresValidos.class);
            
            if (validosRef == null) { // si no esta anotado se cambia el valor
                campoRef.set(objeto, nuevoValor);
                return;
            }

            Object[] valores = parseValues(campoRef.getType(), validosRef.value());
            if (!Arrays.asList(valores).contains(nuevoValor)) {
                throw new RuntimeException("No es un valor válido.");
            }
            // Cambiamos el valor
            campoRef.set(objeto, nuevoValor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Object[] parseValues(Class<?> type, String[] values) throws Exception {
        // Primero convertimos un tipo primitivo a su tipo de clase
        if (type == int.class)
            type = Integer.class;
        else if (type == long.class)
            type = Long.class;
        else if (type == float.class)
            type = Float.class;
        else if (type == double.class)
            type = Double.class;
        // se pueden poner mas tipos primitivos

        // Si el tipo tiene un metodo estatico valueOf(String) o valueOf(Object)
        // podremos
        // parsear el array de strings, sino lanza excepcion.
        Method metodoRef = null;
        try {
            metodoRef = type.getMethod("valueOf", String.class);
        } catch (Exception e) {
            metodoRef = type.getMethod("valueOf", Object.class);
        }
        final Method mref = metodoRef;
        return Stream.of(values).map(value -> {
            try {
                return mref.invoke(null, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).toArray();
    }
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ValoresValidos {
    String[] value() default {}; // rango de valores
}