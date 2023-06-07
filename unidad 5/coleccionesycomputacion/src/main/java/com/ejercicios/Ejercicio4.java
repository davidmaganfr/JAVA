package com.ejercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio4 {
    public static void main(String[] args) {
        List<String> ciudades = List.of("Madrid", "Barcelona", "Sevilla", "Cádiz", "Bilbao", "Lugo");

        //Obtén un array de strings con las ciudades ordenadas alfabéticamente de mayor a menor.
        System.out.println(ciudades.stream()
                                    .sorted(Comparator.reverseOrder())
                                    .toList()); 

        //Obtén una lista de longitudes de los nombres de las ciudades, sin repetidos.
        System.out.println(ciudades.stream()
                                    .distinct()
                                    .map(ciudad -> ciudad.length())
                                    .toList()
                                    );

        //Obtén un valor booleano (true o false) que indique si todos los nombres de ciudad pasan de 5 caracteres.
        System.out.println(ciudades.stream()
                                    .allMatch(ciudad -> ciudad.length() > 5));
                                    
        //4. Junta todos los caracteres de los nombres de ciudades, eliminando caracteres repetidos,
        // y devuelve un string con dichos caracteres ordenados alfabéticamente.?????????????????
        System.out.println(ciudades.stream()
                .map(c -> c.split(""))
                .flatMap(ciudad -> Stream.of(ciudad))
                .distinct()
                .sorted()
                .collect( Collectors.joining(""))); 
        
        //Obtén un Map<Character, List<String>>, cuyas claves serán las iniciales de los nombres de ciudades, 
        //y el valor serán una lista de aquellas ciudades que comienzan por la inicial dada por la clave.
        Map<Character, List<String>> nuevoMap = new HashMap<Character, List<String>>();
        ciudades.forEach(ciudad -> nuevoMap.put(ciudad.charAt(0), ciudades.stream()
                                                                                .filter(c -> c.startsWith(ciudad.substring(0, 1)))
                                                                                .toList())
                                                                                );
        System.out.println(nuevoMap);

        //Mismo ejemplo que el anterior
        System.out.println(ciudades.stream().collect(Collectors.groupingBy(c -> c.charAt(0)))); 
        
    }
}
