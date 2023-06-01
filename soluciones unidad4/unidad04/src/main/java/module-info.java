module unidad04 {
    // Dependencias
    requires menufwork;
    requires lombok;
    // Para que menufwork pueda acceder a los metadatos de la clase Ejercicio1
    exports com.cloudftic to menufwork;
}
