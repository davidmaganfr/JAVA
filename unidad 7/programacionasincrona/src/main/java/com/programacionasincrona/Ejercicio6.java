package com.programacionasincrona;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Stream;

/*
 Implementa una barrera (un objeto CyclicBarrier, donde un conjunto de N hilos deben llegar a la barrera
 y esperar a que lleguen todos los otros para que puedan  continuar su ejecución).

Crea un programa que lance 200 hilos con ForkJoinTask. Cada hilo debe obtener un número entero al azar hasta 
que sea impar y múltiplo de 7 usando un bucle.

Una vez obtenido un número válido, añádelo a una lista sincronizada compartida por todos los hilos. Y el hilo 
debe esperar a que todos los demás hagan lo mismo. A continuación, una vez que todos tienen su número, 
cada hilo imprimirá un mensaje con su número y el mayor número de todos los hilos.

Comprueba que todos los hilos impriman el mismo número mayor.
 */
public class Ejercicio6 {
    public static void main(String[] args) {
        CyclicBarrier barrera = new CyclicBarrier(200);
        Deque<Integer> listaNumeros = new ConcurrentLinkedDeque<>();
        Runnable tarea = () -> {
            int num;
            while (true) {
                num = new Random().nextInt();
                if (num % 2 != 0 && num % 7 == 0) {
                    listaNumeros.add(num);
                    break;
                }
            }
            try {
                barrera.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println("Numero obtenido en el hilo: " + num +
                    " Numero MAYOR obtenido en la tarea: " +
                    listaNumeros.stream().mapToInt(n -> n).max().orElse(0));
            System.out.println(listaNumeros.size());
            Thread.currentThread().interrupt();
        };

        for (int i = 0; i < 200; i++) {
            // var hilo = Executors.defaultThreadFactory().newThread(tarea);
            // hilo.start();
            var hilo = ForkJoinTask.adapt(tarea);
            hilo.fork();
        }
        System.console().readLine();

    }

}
