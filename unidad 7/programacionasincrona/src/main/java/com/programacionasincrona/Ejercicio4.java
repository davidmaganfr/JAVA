package com.programacionasincrona;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;

/*
 * Se quiere crear un programa que calcule dos números primos (uno de 4 dígitos y otro de 5 dígitos) 
 * y que una vez obtenidos calcule la suma de ambos. Para aprovechar la capacidad de cálculo del procesador se 
 * ha decidido obtener cada número primo desde un hilo de ejecución diferente.

Primero ejecuta los dos hilos con ForkJoinPool de forma que se garantice que la suma final se realice sin demora
 en el momento en que ambos números primos estén disponibles.

Ten en cuenta que los hilos ForkJoinTask son demonios y por tanto se destruyen en cuanto acaba el hilo principal. 
Debes hacer que el hilo principal espere a que acaben los hilos.

Después haz lo mismo usando CompletableFuture.
 */
public class Ejercicio4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(withCompletableFuture().get());
        System.out.println(withForkJoinPool().get());
    }

    public static Future<Long> withForkJoinPool() throws InterruptedException, ExecutionException {
        var primo1 = ForkJoinPool.commonPool().submit(() -> primo(4));
        var primo2 = ForkJoinPool.commonPool().submit(() -> primo(5));
        primo1.fork();
        primo2.fork();
        var task = ForkJoinTask.adapt(() -> {
            var result1 = primo1.join();
            var result2 = primo2.join();
            return result1 + result2;
        });
        return task.fork();
    }

    public static Future<Long> withCompletableFuture(){
        var future1 = CompletableFuture.supplyAsync(() -> primo(4));
        var future2 = CompletableFuture.supplyAsync(() -> primo(5));
        return future1.thenCombine(future2, (f1, f2) -> f1 + f2);

    }

    public static long primo(int ndigits) {
        long min = (long) Math.pow(10, ndigits - 1);
        buscaprimo: for (long primo = min; primo < min * 10; primo++) {
            if (primo == 2)
                return primo;
            if (primo < 2 || (primo & 1) == 0)
                continue buscaprimo;
            for (long divisor = 2; divisor < primo / 2; divisor++)
                if (primo % divisor == 0)
                    continue buscaprimo;
            return primo;
        }
        throw new RuntimeException("No existe el primo");
    }
}
