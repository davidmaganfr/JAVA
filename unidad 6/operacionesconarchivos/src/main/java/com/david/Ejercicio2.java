package com.david;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public class Ejercicio2 {
    public static void main(String[] args) {

    }
}

@Data
class GestionVentas {
    private final Path ficheroVentas = Path.of("operacionesconarchivos\\src\\main\\resources\\ventas.txt");

    // Debe agregar los datos de una venta al final del fichero.
    public void agregar(int codigo, String nombre, double precio, LocalDate fecha) {
        String venta = String.format("%1$d, %2$s, %3$f, %4$s",
                codigo, nombre, precio, fecha);
        try {
            var añade = Files.newBufferedWriter(ficheroVentas, StandardOpenOption.APPEND);
            añade.append(venta).append(System.getProperty("line.separator"));
        } catch (IOException e) {
        }
    }

    // Debe recuperar los datos de una venta por su código
    public String encontrar(int codigo) {
        String venta = "";
        try {
            venta = Files.lines(ficheroVentas)
                    .filter(item -> compararCodigo(item, codigo))
                    .findFirst()
                    .orElse(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return venta;

    }

    public boolean compararCodigo(String item, int codigo) {
        return item.split(", ")[0].equals(String.valueOf(codigo));
    }

    // Debe recuperar todas las ventas de un año dado
    public List<String> encontrarPara(int year) {
        try {
            return Files.lines(ficheroVentas)
                    .filter(item -> comprobarAño(item, year) )
                    .toList();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
        
    }

    private boolean comprobarAño(String item, int year) {
       return LocalDate.parse(item.split(", ")[3]).getYear() == year;
    }
}