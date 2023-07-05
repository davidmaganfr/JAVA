package com.david;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PREGUNTA4 {
    private static List<Material> materiales = seleccionarMateriales();

    public static void main(String[] args) {
        //filtra por un subconjunto de materiales compuestos por plastico o arcilloso, cuyo nombre tenga 10 caracteres o mas y un maximos de 10 resultados
        List<Material> subconjunto = materiales.stream()
                .filter(mat -> mat.getTipoMaterial().equals("plastico") ||
                        mat.getTipoMaterial().equals("arcilloso"))
                .filter(mat -> mat.getNombre().length() >= 10)
                .limit(10)
                .toList();

        //Comprueba si existe algun material que sea "arcilloso" en la lista
        boolean existeArcilloso = materiales.stream()
                .anyMatch(mat -> mat.getTipoMaterial().equals("arcilloso"));

        //Obtener un mapa donde la clave es el tipo de material (plastico, arcilloso...) y el valor una lista de objetos Material que estan compuesto
        //por ese tipo de material
        Map<String, List<Material>> grupos = materiales.stream()
                .collect(Collectors.groupingBy(Material::getTipoMaterial));
        
        System.out.println(subconjunto);
        System.out.println(existeArcilloso);
        System.out.println(grupos);
    }

    public static List<Material> seleccionarMateriales() {
        return List.of(
                new Material(1, "casa", "arcilloso"),
                new Material(2, "mesa", "plastico"),
                new Material(3, "cocina", "madera"),
                new Material(4, "habitacion", "arcilloso"));
    }
}
