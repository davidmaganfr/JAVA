package com.cloudftic;

import java.util.List;
import java.util.NoSuchElementException;

/*
Crea un método estático llamado "procesar" con dos argumentos:

Hay que garantizar que el primer argumento sea un número de cualquier tipo válido.
Hay que garantizar que el segundo argumento sea una lista que contenga secuencias de texto.
El método debe retornar la primera secuencia de texto, del mismo tipo que los elementos 
de la lista, cuya longitud coincida con el valor del primer argumento.

Usa genéricos para cumplir con los requerimientos.
 */

public class Ejercicio02 {

    public static void main(String[] args) {
        System.out.println( procesar(4, List.of("uno", "dos", "tres", "cuatro")));
    }

    public static <N extends Number, S extends CharSequence> S procesar(N num, List<S> lista) {
        for (S item : lista) {
            if (item.length() == num.intValue()) {
                return item;
            }
        }
        throw new NoSuchElementException();

        // return lista.stream()
        // .filter(item-> item.length()==num.intValue())
        // .findFirst()
        // .orElseThrow();
    }

}
