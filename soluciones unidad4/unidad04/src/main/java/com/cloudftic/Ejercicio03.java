package com.cloudftic;

import java.util.InputMismatchException;

import lombok.Data;

/*
Un programa define el siguiente tipo de excepción personalizado:

public class EdadFueraRango extends RuntimeException {
    ....
}
Esta clase debe permitir recuperar el valor de una edad que se considera fuera de 
rango. Crea el siguiente método:

public static int solicitarEdad(int edadMinima, int edadMaxima) throws EdadFueraRango {
     System.out.printf("Escribe una edad entre %d y %d:\n", edadMinima, edadMaxima);
     int edad = java.util.Scanner(System.in).nextInt();
     .....
}
Debe retorna la edad escrita por el usuario o lanzar la excepción si la edad está 
fuera de rango.

En el método main() solicita una edad usando el método solicitarEdad() hasta que 
sea válida. 
 */

public class Ejercicio03 {

    public static void main(String[] args) {
        do {
            try {
                int edad = solicitarEdad(1, 60);
                System.out.printf("La edad es: %d\n", edad);
                break;
            } catch (EdadFueraRango e) {
                System.out.printf("Error, la edad no es válida: '%s'\n", e.getEdad());
            }
        } while (true);
    }

    public static int solicitarEdad(int edadMinima, int edadMaxima) throws EdadFueraRango {
        System.out.printf("Escribe una edad entre %d y %d:\n", edadMinima, edadMaxima);
        try {
            int edad = new java.util.Scanner(System.in).nextInt();
            if (edad < edadMinima || edad > edadMaxima) {
                throw new EdadFueraRango(String.valueOf(edad));
            }
            return edad;
        } catch (InputMismatchException ex) {
            throw new EdadFueraRango("No es un numero.");
        }
    }
}

class EdadFueraRango extends RuntimeException {
    private final String edad;

    public EdadFueraRango(String edad) {
        this.edad = edad;
    }

    public String getEdad() {
        return edad;
    }

}