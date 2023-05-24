package com.david;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ejercicio7 {
    public static void main(String[] args) {
        long instanteInicial = System.currentTimeMillis();
        System.getProperties().list(System.out); // Listado de las propiedades del sistema:

        inicializarConfiguracion();

        long instanteFinal = System.currentTimeMillis();
        finalizarConfiguracion(instanteFinal - instanteInicial);

        eliminarPreferencias();
    }

    private static void inicializarConfiguracion() {
        // LEYENDO EL FICHERO DE CONFIGURACION
        String carpetaUsuario = System.getProperty("user.home");
        Path rutaFichero = Path.of(carpetaUsuario, "configuraciones.properties");
        Properties conf = new Properties();
        try {
            conf.load(new FileReader(rutaFichero.toFile()));
            String ultimoAcceso = (String) conf.getOrDefault("ultimoAcceso", "");
            System.out.println("Ultimo acceso: ".concat(ultimoAcceso));
        } catch (IOException e) {
            System.out.println("Es tu primera ejecución.");
        }

        // LEYENDO PREFERENCIAS DE USUARIO
        long duracion = Preferences.userRoot()
                .node("configuraciones")
                .getLong("duracion", 0);
        System.out.printf("La duración previa fue de %d milisegundos.", duracion);
    }

    private static void finalizarConfiguracion(long msDuracion) {
        // ESCRIBIENDO AL FICHERO DE CONFIGURACION
        String carpetaUsuario = System.getProperty("user.home");
        Path rutaFichero = Path.of(carpetaUsuario, "configuraciones.properties");
        Properties conf = new Properties();
        conf.setProperty("ultimoAcceso", LocalDateTime.now().toString());

        try {
            conf.store(new FileWriter(rutaFichero.toFile()), null);
        } catch (IOException e) {
        }
        // ESCRIBIENDO PREFERENCIAS DE USUARIO
        Preferences.userRoot()
                .node("configuraciones")
                .putLong("duracion", msDuracion);
    }

    private static void eliminarPreferencias() {
        try {
            Preferences.userRoot()
                    .node("configuraciones")
                    .removeNode();
        } catch (BackingStoreException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
