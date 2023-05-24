package com.david;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;


/* Lee el fichero adjunto "articulo.txt" que contiene varias líneas de texto. Y encadena las siguientes operaciones:

1. Trocea el texto en palabras (están separadas por espacios en blanco). Si quedan palabras en blanco no debes incluirlas.
2. Cuenta cuántas palabras tiene el texto.
3. Muestra todas las palabras que comiencen por una mayúscula.
4. Convierte todo el texto a mayúsculas.
5. Cuenta cuántas palabras comienzan por "ES".
6. Elimina del texto la palabra "EN".
7. Vuelve a contar el número de palabras.
Crea un método para cada una de estas operaciones. */

public class ejercicio1 {
    static final String contenido = ejercicio1.leer("articulo.txt");
    static final String PATRONREG = "[\\s\\n\\p{Punct}]+";

    public static String leer(String rutaFichero) {
        try {
            URL ruta = App.class.getClassLoader().getResource(rutaFichero);
            return Files.readString(Path.of(ruta.toURI()));
        } catch (Exception e) {
            return "No se pudo leer: " + e;
        }
    }

    // 1. Trocea el texto en palabras (están separadas por espacios en blanco). Si
    // quedan palabras en blanco no debes incluirlas.
    public static List<String> trocearTextoPalabras() {
        List<String> listaTrozos = new ArrayList<String>();
        var escaner = new Scanner(contenido);
        while (escaner.hasNextLine()) {
            var line = escaner.nextLine().split(" ");
            for (String s : line) {
                if (!s.isBlank()) {
                    listaTrozos.add(s);
                }
            }
        }
        escaner.close();
        return listaTrozos;
    }

    // 2. Cuenta cuántas palabras tiene el texto.
    public static int contarPalabras(String texto) {
        Scanner sc = new Scanner(texto);
        sc.useDelimiter(PATRONREG); // utiliza como separadores lo que aparece en el parametro de la expresion regular
        int total = 0;
        while (sc.hasNext()) {
            sc.next();
            total++;
        }
        ;
        sc.close();
        return total;
    }

    // 3. Muestra todas las palabras que comiencen por una mayúscula. (ver resultado
    // no me cuadra)
    public static List<String> palabrasConMayuscula(String contenido) {
        List<String> listaActualizada = new ArrayList<String>();
        String[] listaPalabras = contenido.split(PATRONREG); 
        for (String word : listaPalabras) {
            if (Character.isUpperCase(word.charAt(0))) {
                listaActualizada.add(word);
            }
        }
        return listaActualizada;
    }

    // 4. Convierte todo el texto a mayúsculas.
    public static String convertirTodoMayusculas() {
        return contenido.toUpperCase();
    }

    // 5. Cuenta cuántas palabras comienzan por "ES".
    public static int cuantasComienzanCon() {
        var coincidenciascon_ES = Pattern.compile("\\bES").matcher(contenido).results().count();
        return (int) coincidenciascon_ES;
    }

    // 6. Elimina del texto la palabra "EN".
    public static String eliminarPalabra() {
        var eliminar = Pattern.compile("\\bEN\\b").matcher(contenido).replaceAll("");
        return eliminar;
    }

}
