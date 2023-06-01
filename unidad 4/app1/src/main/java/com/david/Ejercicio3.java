package com.david;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class Ejercicio3 {
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println(solicitarEdad(10, 50, () -> new Scanner(System.in).nextInt()));
                break;
            } catch (EdadFueraRango e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int solicitarEdad(int edadMinima, int edadMaxima, Supplier<Integer> readEdad) throws EdadFueraRango {
        System.out.printf("Escribe una edad entre %d y %d:\n", edadMinima, edadMaxima);
        int edad = readEdad.get();
        if (edad < edadMinima || edad > edadMaxima) {
            throw new EdadFueraRango(edad, edadMinima, edadMaxima);
        }
        return edad;
    }
}

class EdadFueraRango extends RuntimeException {
    public EdadFueraRango(int edad, int edadMinima, int edadMaxima) {
        super(String.format("La edad %1d esta fuera del rango [%2d, %3d]", edad, edadMinima, edadMaxima));
    }
}
