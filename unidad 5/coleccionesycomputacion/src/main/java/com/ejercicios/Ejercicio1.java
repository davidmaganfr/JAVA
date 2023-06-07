package com.ejercicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class Ejercicio1 {
    public static void main(String[] args) {
        Empleado empleado1 = new Empleado("04923539a", "Daniel", 2000.0);
        Empleado empleado2 = new Empleado("039234329a", "Javier", 1550.23);
        Empleado empleado3 = new Empleado("05923539a", "David", 2532.34);

        ListaEmpleados lista = new ListaEmpleados();
        lista.add(empleado1);
        lista.add(empleado2);
        lista.add(empleado3);

        lista.ordenaPorSueldo();
        System.out.println(lista.sueldoMedioEmpiezanPor('D'));

    }
}

@Data
@AllArgsConstructor
class Empleado implements Comparable<Empleado> {
    private String nif;
    private String nombre;
    private Double sueldo;
  
    @Override
    public int compareTo(Empleado o) {
        return nif.compareTo(o.nif);
    }
}

class ListaEmpleados extends ArrayList<Empleado> {
    public void ordenaPorNombre(){
        // debe dejar ordenada la lista de empleados por su nombre en orden alfabetico ascendente.
        this.sort(Comparator.comparing(Empleado::getNombre));
    }
    public void ordenaPorSueldo(){
        //Debe dejar la lista ordenada por sueldos en orden ascendente.
        this.sort(Comparator.comparing(Empleado::getSueldo));
    }
    public Empleado getEmpleadoMayorSueldo(){
        //Debe retornar el empleado con mayor sueldo
        var mayorSueldo = this.stream().max(Comparator.comparing(Empleado::getSueldo)).get();
        return mayorSueldo;
    }
    public List<Empleado> empleadosConSueldo(double sueldoMaximo){
        //Debe retornar los empleados con un sueldo menor o igual al sueldo del parametro
        List<Empleado> nuevaLista = new ArrayList<Empleado>();
        this.stream()
        .filter(em -> em.getSueldo() <= sueldoMaximo)
        .forEach(empleado -> nuevaLista.add(empleado));
        return nuevaLista;
    }
    public double sueldoMedioEmpiezanPor(char primeraLetra){
        //Debe retornar el sueldo medio de aquellos empleados que comiencen por la letra 
        //indicada en el primer argumento
        var empleadosConLetra = this.stream()
                                    .filter(empleado -> empleado.getNombre().startsWith(String.valueOf(primeraLetra)))
                                    .collect(Collectors.averagingDouble(Empleado::getSueldo));
                                    ;
        
        return empleadosConLetra;
    }

}
