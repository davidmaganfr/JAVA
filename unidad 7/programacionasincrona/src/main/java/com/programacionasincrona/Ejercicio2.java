package com.programacionasincrona;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/*Implementa un esquema productor/consumidor. Es decir, habrá un hilo que se dedicará a crear datos y otros 
a consumirlos.
Crea un hilo productor que debe generar valores del 1 al 100 cada 300 milisegundos. Los valores deben  ser 
metidos en una cola sincronizada.
Crea dos hilos consumidores. Cada uno de ellos debe sacar el primer número de la cola sincronizada lo antes
 posible e imprimirlo con un mensaje que identifique al consumidor.
Hay que optimizar el uso de los hilos para que los consumidores estén dormidos si no hay ningún valor en la cola
sincronizada.

 */
public class Ejercicio2 {
    public static void main(String[] args) {
        BlockingQueue<Integer> valores = new ArrayBlockingQueue<>(100);
        AtomicInteger contador = new AtomicInteger();

        var hiloProductor = Executors.newSingleThreadScheduledExecutor();
        // CONSUMIDORES
        Thread hiloConsumidor1 = new Thread(() -> {
            while (!hiloProductor.isShutdown()) {
                try {
                    System.out.println("Hilo consumidor 1: " + valores.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        Thread hiloConsumidor2 = new Thread(() -> {
            while (!hiloProductor.isShutdown()) {
                try {
                    System.out.println("Hilo consumidor 2: " + valores.take());
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        // PRODUCTOR
        Runnable generadorValores = () -> {
            int incremento = contador.incrementAndGet();
            if (incremento <= 10) {
                valores.add(incremento);
            } else {
                hiloProductor.shutdownNow();
                hiloConsumidor1.interrupt();
                hiloConsumidor2.interrupt();
            }
        };
        hiloProductor.scheduleAtFixedRate(generadorValores, 0, 300, TimeUnit.MILLISECONDS);

        hiloConsumidor1.start();
        hiloConsumidor2.start();

    }

}
