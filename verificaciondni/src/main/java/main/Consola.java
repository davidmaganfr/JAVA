package main;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Consola {

    /**
     * Lee un texto a trav&eacute;s de teclado hasta que se pulsa la tecla Intro.
     * 
     * @param message Un mensaje que se mostrar&aacute; al principio.
     * @return El texto escrito por teclado
     */
    public static String readText(Object... message) {
        print(message);
        return new java.util.Scanner(System.in).nextLine();
    }

    /**
     * Lee un n&uacute;mero entero a trav&eacute;s de teclado hasta que se pulsa la
     * tecla Intro.
     * 
     * @param message Un mensaje que se mostrar&aacute; al principio.
     * @return El n&uacute;mero escrito como un int.
     */
    public static int readInteger(Object... message) {
        print(message);
        return new java.util.Scanner(System.in).nextInt();
    }

    /**
     * Lee un n&uacute;mero con decimales a trav&eacute;s de teclado hasta que se
     * pulsa
     * la tecla Intro.
     * 
     * @param message Un mensaje que se mostrar&aacute; al principio.
     * @return El n&uacute;mero escrito como un double.
     */
    public static double readReal(Object... message) {
        print(message);
        return new java.util.Scanner(System.in).nextDouble();
    }

    /**
     * Imprime los argumentos en una línea de texto separándolos con un espacio.
     * 
     * @param args Una secuencia (separada por comas) o un array de valores.
     */
    public static void print(Object... args) {
        System.out.printf(
                Stream.generate(() -> "%s").limit(args.length)
                        .collect(Collectors.joining(" ")),
                args);
    }

    /**
     * Imprime los argumentos en una línea de texto separándolos con un espacio, y
     * provando un salto de línea al final.
     * 
     * @param args Una secuencia (separada por comas) o un array de valores.
     */
    public static void println(Object... args) {
        System.out.printf(
                Stream.generate(() -> "%s").limit(args.length)
                        .collect(Collectors.joining(" "))
                        + System.getProperty("line.separator"),
                args);
    }
}
