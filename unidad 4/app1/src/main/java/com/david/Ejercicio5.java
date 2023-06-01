package com.david;

import lombok.Data;

@Data
public class Ejercicio5 {
    public static void main(String[] args)
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Ejercicio5 user = new Ejercicio5();
        System.out.println(getValorCampo(user, "name"));
    }

    private String name = "David";
    private String lastName = "Magan";
    private String last2 = "Fedez";

    /*
     * Este método debe usar técnicas de reflexión para explorar el objeto dado y
     * retornar
     * el valor en un campo que se corresponda con el nombre dado. Si el objeto es
     * null o si el campo
     * no existe debe retornar null.
     */
    public static Object getValorCampo(Ejercicio5 objeto, String nombreCampo) {
        try {
            var acces = Ejercicio5.class.getDeclaredField(nombreCampo);
            acces.setAccessible(true);
            return acces.get(objeto);
        } catch (Exception e) {
        }
        return null;
    }
}
