package main;

import java.util.Arrays;

/*
 * Crea un método que nos devuelva la secuencia de Fibonacci de un número dado, 
 * y dada la cantidad de números de la secuencia. El resultado será devuelto como un array:

int[] resultado = fibbonacci(4, 6)

La secuencia de Fibonacci parte de un número y su siguiente, y va añadiendo números como suma 
de los dos previos. Por ejemplo, el resultado sería:

[4, 5, 9, 14, 23, 37]
 */

public class Ejercicio4 {
    public static void main(String[] args) {
        int [] resultado = fibonacci(12, 6);
        System.out.println(Arrays.toString(resultado));
    }
    static int[] fibonacci(int numero, int tamaño){
        int[] resultado = new int[tamaño];
        resultado[0] = numero;
        resultado[1] = numero + 1;
        for(int i = 2; i < resultado.length; i++){
            resultado[i] = resultado[i - 1] + resultado[i - 2];
        }
        return resultado;
    }
}
