package com.work;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FrameworkMenu {
    private static Object instanceMenuClass; // una instancia de la clase que procesa el menú
    private static int numOptions; // número de opciones totales del menú
    private static Map<Integer, Method> methods; // Un mapa que asocia {opcion, método}
    private static List<String> options; // Lista con el texto de las opciones de menú

    // Método principal.
    // Instancia un objeto de la clase que procesa el menú y ejecuta sus métodos.
    public static void run(Class menuClass) throws Exception {
        options = tryGetOptions(menuClass);
        instanceMenuClass = menuClass.getDeclaredConstructor().newInstance();
        numOptions = options.size();
        methods = getMethods(menuClass);

        boolean end;
        do {
            printMenu();
            int option = inputOption();
            end = processOption(option);
        } while (!end);
    }

    // Recupera el texto de las opciones del menú, o lanza una excepción.
    private static List<String> tryGetOptions(Class menuClass) {
        var menu = (MenuOptions) menuClass.getAnnotation(MenuOptions.class);
        if (menu == null) {
            throw new RuntimeException("Clase no soportada.");
        }
        return Stream.concat(Stream.of(menu.value()), Stream.of("Salir")).toList();
    }

    // Obtiene un mapa que asocia el índice de la opción del menú con un método de
    // la clase dada
    private static Map<Integer, Method> getMethods(Class<?> menuClass) {
        return Stream.of(menuClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(MenuAction.class))
                .collect(Collectors.groupingBy(
                        m -> m.getDeclaredAnnotation(MenuAction.class).value(),
                        Collectors.reducing(null, (a, b) -> b)));
    }

    // Imprime las opciones del menú
    private static void printMenu() {
        IntStream.range(0, numOptions)
                .forEach(index -> System.out.printf("%d. %s\n", index + 1, options.get(index)));
    }

    // Solita una opción de menú por teclado
    private static int inputOption() {
        return Integer.parseInt(System.console().readLine("Escriba la opción entre 1 y %d: ", numOptions));
    }

    // Ejecuta el método asociado con la opción dada, o no hace nada si no existe
    private static boolean processOption(int option) throws Exception {
        if (option == numOptions) {
            return true; // finalizar
        }
        Method method = methods.get(option);
        if (method == null)
            return false; // si no existe el método continúa el menú
        method.invoke(instanceMenuClass); // Ejecuta el método
        return false;
    }
}
