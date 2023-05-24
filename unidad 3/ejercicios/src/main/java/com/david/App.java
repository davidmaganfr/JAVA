package com.david;

import java.text.ParseException;

public class App {
    public static void main(String[] args) throws ParseException {

        // ejercicio1.trocearTextoPalabras();

        int numeroPalabrasTexto = ejercicio1.contarPalabras(ejercicio1.contenido);
        System.out.printf("El texto tiene %d palabras\n", numeroPalabrasTexto);

        // System.out.println(ejercicio1.palabrasConMayuscula("Hola, estoy en Casa"));
        // ejercicio1.convertirTodoMayusculas();

        // int buscadorpalabras = ejercicio1.cuantasComienzanCon();
        // System.out.printf("Existen %d palabras que comienzan con 'ES'", buscadorpalabras);

        // String eliminarPalabras = ejercicio1.eliminarPalabra();
        // System.out.println(eliminarPalabras);

        // int numeroPalabrasTexto2 = ejercicio1.contarPalabras(eliminarPalabras);
        // System.out.printf("El texto tiene %d palabras\n", numeroPalabrasTexto2);

    }

}
