package com.ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

public class Ejercicio2 {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
    

    }
}

class RepositorioDepartamentos {
    private static Map<String, List<String>> departamentos = new HashMap<>();
    static {
        // Datos de prueba
        departamentos.put("Informatica", new ArrayList<>(List.of("Juan", "María")));
        departamentos.put("Finanzas", new ArrayList<>());
        departamentos.put("Comercio", null);
    }

    public void addDepartamento(String departamento) {
        // Debe añadir un nuevo departamento si aún no existe
        if (!departamentos.containsKey(departamento)) {
            departamentos.put(departamento, new ArrayList<>());
        }
    }

    public void addEmpleado(String departamento, String empleado) {
        // Debe añadir un nuevo empleado al departamento dado.
        // Si aún no existe el departamento hay que añadirlo también.
        // if (!departamentos.containsKey(departamento)) {
        //     departamentos.put(departamento, new ArrayList<>()).add(empleado);
        // }
        // departamentos.get(departamento).add(empleado);
            departamentos.compute(departamento, (k,v) -> {if(v == null) v = new ArrayList<>(); v.add(empleado); return v;});

    }

    public Map<String, Integer> obtenerNumeroEmpleadosPorDepartamento() {
        // Debe retornar un mapa con el nombre del departamento y su número de
        // empleados.
        Map<String, Integer> nuevoMap = new HashMap<>();
        departamentos.forEach((departamento, empleados) -> nuevoMap.put(departamento, empleados.size()));

        return nuevoMap;
    }
}
