package com.ejercicios;

import java.util.stream.IntStream;

public class Ejercicio5 {
    public static void main(String[] args) {
        //Flujo infinito de numeros impares
        IntStream flujo = IntStream.iterate(0, n -> n + 1)
                                    .filter(num -> num % 2 != 0);

        //1. Obtén la suma de los primeros 100 impares del flujo. (El resultado debe ser 10000)
        System.out.println(flujo.limit(100)
                                 .sum()); 
        
        //2. Filtra por los primeros 21 números múltiplos de 5 y obtén su valor medio. (El resultado debe ser 105.0)
        IntStream flujo1 = IntStream.iterate(0, n -> n + 1)
                                    .filter(num -> num % 2 != 0);
        System.out.println(flujo1.filter(num -> num%5 == 0)
                                .limit(21)
                                .average()
                                .getAsDouble());

        //Define una variable x y asígnale un número entero al azar mayor o igual que cero . 
        //A continuación obtén el primer impar que sea múltiplo de 7 ubicado dentro del flujo 
        //entre las posiciones x y x+10000 (inclusive). Se piden posiciones concretas de los elementos del flujo, 
        //no valores concretos.
        IntStream flujo3 = IntStream.iterate(0, n -> n + 1)
                                    .filter(num -> num % 2 != 0);
        int x = 134;
        System.out.println(flujo3
                                .skip(x)
                                .limit(x + 10000)
                                .filter(num -> num % 7 == 0)
                                .findFirst()
                                );
    }



}
