package com.david;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/* Lee el fichero adjunto "articulo.txt" que contiene varias líneas de texto. Y encadena las siguientes 
operaciones usando expresiones regulares:

1. Cuenta cuántas palabras tiene el texto antes de modificarlo.
2. Localiza y recupera en una lista todas las siglas encontradas en el texto, por ejemplo: SIDA, VIH, OMS, IAVI, GSK, etc.
3. Recupera en una lista todos los signos de puntuación (.,;:¡!¿?...) que hay dentro del texto.
4. Convierte todo el texto a mayúsculas.
5. Sustituye las letras acentuadas (ÁÉÍÓÚ) por la misma letra sin acento.
6. Elimina todos los números.
7. Eliminar del texto todas las palabras incluidas en el fichero adjunto  “stopwords.txt”.
8. Se quiere puntuar el texto. Para ello se incrementará la puntuación con las palabras positivas y se decrementará con 
las palabra negativas. Para ello ten en cuenta las palabras y su puntuación de los 
ficheros "positive_lex.txt" y "negative_lex.txt". */

public class ejercicio4 {
    public static void main(String[] args) throws IOException, URISyntaxException {
        URL ruta = App.class.getClassLoader().getResource("articulo.txt");
        String text = Files.readString(Path.of(ruta.toURI()));

        URL ruta2 = App.class.getClassLoader().getResource("stopwords.txt");
        String stopwords1 = Files.readString(Path.of(ruta2.toURI()));
        String[] stopwords = stopwords1.split("\n");

        URL ruta3 = App.class.getClassLoader().getResource("positive_lex.txt");
        String positive = Files.readString(Path.of(ruta3.toURI()));

        URL ruta4 = App.class.getClassLoader().getResource("negative_lex.txt");
        String negative = Files.readString(Path.of(ruta4.toURI()));
       

        System.out.println(contarPalabras(text));
        System.out.println(siglasEnTexto(text));
        System.out.println(signosPuntuacion(text));
        System.out.println(convertirTodoMayusculas(text));
        System.out.println(sustituirLetrasAcentuadas(text));
        System.out.println(text);
        System.out.println(eliminarNumeros(text));
        System.out.println(eliminarPalabrasCoincidencia(text, stopwords)); 
        Map<String, Double> uno = Map.of("uno", 2.1, "dos", 2.2);
        Map<String, Double> dos = Map.of("e", 1.5, "l", 2.2);
        System.out.println(puntuarTexto(text, uno, dos)); 
    }

    private static Map<String, Double> stringToMap(String text) {
        Map<String, Double> mapa = new HashMap<String, Double>();
        String[] parts = text.split("\n");
        for(String part : parts){
            String[] sep = part.split(" ");
            String key = sep[0].trim();
            Double val = Double.parseDouble(sep[1].trim());
            mapa.put(key, val);
        }
        return mapa;
    }

    // 8. Puntuar texto en base a los archivos negative_lex.txt y
    // positive_lex.txt//positive.
    private static double puntuarTexto(String text, Map<String, Double> positive, Map<String, Double> negative) {
        positive.putAll(negative);
        String a = positive.keySet().stream()
                .collect(Collectors
                .joining("|", "(", ")"));
        return Pattern.compile("(?<=[^ñ])\\b" + a + "\\b(?=[^ñ])")
                .matcher(text)
                .results()
                .mapToDouble(n -> positive.get(n.group()))
                .sum();
    }
    // 7. Eliminar palabras que coincidan con las del archivo stopwords.txt
    private static String eliminarPalabrasCoincidencia(String text, String[] stopwords) {
        // String[] stopwordsList = stopwords.split("\n");
        String a = Stream.of(stopwords)
                .collect(Collectors
                        .joining("|", "(", ")"));
        return Pattern.compile("(?<=[^ñ])\\b" + a + "\\b(?=[^ñ])")
                .matcher(text)
                .replaceAll("");
        // String newText = text;
        // for(String word : stopwordsList) {
        // newText =
        // Pattern.compile("\\b[^ñ]"+word+"[^ñ]\\b").matcher(newText).replaceAll("");
        // }
        // return newText;
    }

    // 6. Eliminar todos los numeros
    private static String eliminarNumeros(String text) {
        var numeros = Pattern.compile("\\d").matcher(text).replaceAll("");
        return numeros;
    }

    // 5. Sustituye las letras acentuadas (ÁÉÍÓÚ) por la misma letra sin acento.
    private static String sustituirLetrasAcentuadas(String text) {
        Map<String, String> map = Map.of("á", "a", "é", "e", "í", "i", "ó", "o", "ú", "u");
        var letrasAcentuadas = Pattern.compile("[áéíóú]").matcher(text).replaceAll(wr -> map.get(wr.group()));
        return letrasAcentuadas;
    }

    // 4. Convierte todo el texto a mayúsculas.
    private static String convertirTodoMayusculas(String text) {
        var mayusculas = Pattern.compile("[a-z]").matcher(text).replaceAll(wr -> wr.group().toUpperCase());
        return mayusculas;
    }

    // 3. Recupera en una lista todos los signos de puntuación (.,;:¡!¿?...) que hay
    // dentro del texto.
    private static List<String> signosPuntuacion(String text) {
        var signos = Pattern.compile("\\p{Punct}").matcher(text).results().map(mr -> mr.group()).toList();
        return signos;

    }

    // 2. Localiza y recupera en una lista todas las siglas encontradas en el texto,
    // por ejemplo: SIDA, VIH, OMS, IAVI, GSK, etc.
    private static List<String> siglasEnTexto(String text) {
        var siglas = Pattern.compile("\\b[A-ZÑ]{2,}?\\b").matcher(text).results().map(mr -> mr.group()).toList();
        return siglas;
    }

    // 1. Cuenta cuántas palabras tiene el texto antes de modificarlo.
    private static int contarPalabras(String text) {
        var count = Pattern.compile("\\b[a-zñA-ZÑáéíóú]+\\b").matcher(text).results().count();
        return (int) count;
    }

}
