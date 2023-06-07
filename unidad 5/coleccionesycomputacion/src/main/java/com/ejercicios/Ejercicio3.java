package com.ejercicios;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Recibe como argumento una lista de números enteros. Debe retornar un string formado por aquellos 
 * dígitos (en orden ascendente) que no aparezcan en ninguno de los números que hay dentro de la lista. 
 * Si los números incluyen todos los dígitos (del 0 al 9) debe retornar un string vacío. 
 * Por ejemplo, si numeros=[1201, 23045], debe retornar "6789".
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println(Ejercicio3.digitosQueNoEstanEn(List.of(135332,34532423,543492873))); 
    }
    public static String digitosQueNoEstanEn(List<Integer> numeros){
        String[] referencia = "0123456789".split("");
        var convertirString = numeros.stream()
                                        .map(num -> num.toString())
                                        .map(str -> str.split(""))
                                        .flatMap(n -> Stream.of(n))
                                        .distinct()
                                        .sorted()
                                        .collect(Collectors.joining(""));
        
        return Stream.of(referencia).filter(n -> !convertirString.contains(n))
                            .collect(Collectors.joining(""));
    }
}
