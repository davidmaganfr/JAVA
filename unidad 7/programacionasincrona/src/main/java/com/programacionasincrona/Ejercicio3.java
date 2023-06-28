package com.programacionasincrona;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;

//El siguiente método debe leer dos ficheros de texto en paralelo mediante operaciones asíncronas y 
//retorna la concatenación de los contenidos.
//Utiliza la clase CompletableFuture para retornar el objeto Future que proporcione el texto concatenado.

public class Ejercicio3 {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        
        // System.out.println(readAsync(
        //         Path.of("programacionasincrona\\src\\main\\resources\\texto.txt"),
        //         Path.of("programacionasincrona\\src\\main\\resources\\texto2.txt")).get());
        System.out.println(readCompletableFuture(
                Path.of("programacionasincrona\\src\\main\\resources\\texto.txt"),
                Path.of("programacionasincrona\\src\\main\\resources\\texto2.txt")).get());
    }

    public static Future<String> readAsync(Path ruta1, Path ruta2) throws IOException {
        var executor = Executors.newSingleThreadExecutor();
        var tarea1 = executor.submit(() -> {
            try {
                return Files.readString(ruta1);
            } catch (IOException e) {
                return "";
            }
        });
        var tarea2 = executor.submit(() -> {
            try {
                return Files.readString(ruta2);
            } catch (IOException e) {
                return "";
            }
        });
        var result = executor.submit(() -> {
            return tarea1.get() + tarea2.get();
        });
        
        executor.shutdown();
        return result;
    }

    public static Future<String> readCompletableFuture(Path ruta1, Path ruta2) throws IOException {
        var tarea1 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(ruta1);
            } catch (IOException e) {
                return "";            
            }
        });
        var tarea2 = CompletableFuture.supplyAsync(() -> {
            try {
                return Files.readString(ruta2);
            } catch (IOException e) {
                return "";            
            }
        });
        return tarea1.thenCombine(tarea2, (t1, t2) -> t1 + t2);
        
    }
}
