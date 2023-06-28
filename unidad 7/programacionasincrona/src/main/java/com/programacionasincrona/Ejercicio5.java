package com.programacionasincrona;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import lombok.Getter;

public class Ejercicio5 {
    public static void main(String[] args) {
        var liebre = Executors.newSingleThreadScheduledExecutor();
        var tortuga = Executors.newSingleThreadScheduledExecutor();

        Supplier<String> avanceLiebre = () -> {
            int pasosLiebre = 0;
            while (pasosLiebre <= 100) {
                pasosLiebre += SucesoLiebre.sucesoAzar().getPasos();
                System.out.println("Pasos liebre: " + pasosLiebre);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
                if (pasosLiebre == 100) {
                    break;
                }
            }
            return "Liebre";
        };

        Supplier<String> avanceTortuga = () -> {
            int pasosTortuga = 0;
            while (pasosTortuga <= 100) {
                pasosTortuga += SucesoTortuga.sucesoAzar().getPasos();
                System.out.println("Pasos tortuga: " + pasosTortuga);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
                if (pasosTortuga == 100) {
                    break;
                }
            }
            return "Tortuga";
        };
        var futureLiebre = CompletableFuture.supplyAsync(avanceLiebre);
        var futureTortuga = CompletableFuture.supplyAsync(avanceTortuga);

        CompletableFuture.anyOf(futureLiebre, futureTortuga).thenAccept(n -> {
            System.out.println("Ha ganado: " + n);
            futureLiebre.cancel(true);
            futureTortuga.cancel(true);
        }).join();

    }
}

enum SucesoTortuga {
    AvanceRapido(50, 3), Resbalo(70, -6), AvanceLento(100, 1);

    private static Random random = new Random();
    private final int probabilidadDelta;
    private @Getter final int pasos;

    private SucesoTortuga(int probabilidad, int pasos) {
        this.probabilidadDelta = probabilidad;
        this.pasos = pasos;
    }

    public static SucesoTortuga sucesoAzar() {
        int probabilidad = random.nextInt(100);
        for (var suceso : SucesoTortuga.values()) {
            if (probabilidad < suceso.probabilidadDelta)
                return suceso;
        }
        return null;
    }
}

enum SucesoLiebre {
    Duerme(20, 0), GranSalto(40, 9), ResbalonGrande(50, -12),
    PequenoSalto(80, 1), ResbaloPequeno(100, -2);

    private static Random random = new Random();
    private @Getter final int probabilidadDelta;
    private @Getter final int pasos;

    private SucesoLiebre(int probabilidad, int pasos) {
        this.probabilidadDelta = probabilidad;
        this.pasos = pasos;
    }

    public static SucesoLiebre sucesoAzar() {
        int probabilidad = random.nextInt(100);
        for (var suceso : SucesoLiebre.values()) {
            if (probabilidad < suceso.probabilidadDelta)
                return suceso;
        }
        return null;
    }
}
