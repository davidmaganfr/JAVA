package com.programacionasincrona;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


//HILOS THREAD
/*
 * Implementa un programa que lance 12 hilos simultáneamente, cada uno de los cuales debe escribir un
 *  "hola mundo" seguido del identificador del hilo.
Lanza 6 hilos usando objetos Thread y Runnable, y otros 6 hilos usando un ExecutorService y Callable.
¿Se nota alguna diferencia al usar cada técnica?
 */
public class Ejercicio1 {
    static int contadorExecutor = 0;
    public static void main(String[] args) throws InterruptedException {
        conThread();
        conExecutorService();
    }
    public static void conThread() {
        Runnable tarea = () -> {
             System.out.println("Hola mundo id thread: " + Thread.currentThread().threadId());
        };
        var thread = Executors.defaultThreadFactory();
        IntStream.range(0, 6)
                .mapToObj(i -> thread.newThread(tarea))
                .forEach(hilo -> hilo.start());
    }
    public static void conExecutorService() throws InterruptedException{
        Callable<Object> tareaExecutor = Executors.callable(() -> System.out.println("Hola mundo, id Executor: " + Thread.currentThread().threadId()));
        List<Callable<Object>> tareas = Stream.generate(() -> tareaExecutor) 
                                                .limit(6)
                                                .toList();
        ExecutorService hilosExecutor = Executors.newFixedThreadPool(6);
        hilosExecutor.invokeAll(tareas); //metodo bloqueante
        hilosExecutor.shutdown(); 
    }
}
