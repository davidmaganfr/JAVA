package com.david;

import java.io.IOException;


public class ejercicio6 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
      // Aqui hay que poner la ruta donde estan los paquetes del proyecto
      String classpath = "-cp C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\JAVA\\unidad 3\\ejercicios\\target\\classes";
      Process proceso = runtime.exec(String.format("java %s com.david.Suma 4 6", classpath));
      int code = proceso.waitFor();
      System.out.printf("Resultado de la suma: %d\n", code);
    }
}


