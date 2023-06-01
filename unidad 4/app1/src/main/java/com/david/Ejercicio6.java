package com.david;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class Ejercicio6 {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        Ejercicio6 objeto = new Ejercicio6();
        changeValue(objeto, "campo1", "5");
        System.out.println(objeto.campo1);
    }

    @ValoresValidos(value = { "1", "2", "3" })
    public String campo1;
    @ValoresValidos(value = { "1", "2", "3" })
    public String campo2;

    /*
     * Este método se utilizará para cambiar el valor de un campo de un objeto.
     * Si el campo no está anotado con @ValoresValidos se modificará su valor.
     * Si está anotado se debe comprobar previamente si el nuevo valor es válido.
     * Si no es válido el método debe lanzar una excepción.
     */
    public static void changeValue(Object objeto, String nombreCampo, Object nuevoValor) {
        try {
            Field campo = objeto.getClass().getDeclaredField(nombreCampo);
            var validos = campo.getDeclaredAnnotation(ValoresValidos.class);

            if (validos == null) {
                campo.set(objeto, nuevoValor);
                return;
            }
            boolean comprobacion = false;
            for (var valor : validos.value()) {
                Object v = String.valueOf(valor);
                if (v.equals(nuevoValor)) {
                    comprobacion = true;
                    campo.set(objeto, nuevoValor);
                }
            }

            if (!comprobacion) {
                throw new RuntimeException("No es un valor valido.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ValoresValidos {
    Object[] value();
}
