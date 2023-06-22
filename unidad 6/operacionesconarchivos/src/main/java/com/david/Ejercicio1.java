package com.david;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio1 {
    public static void main(String[] args) {
        Path ruta = Path.of("C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\U6ejer");
        eliminar(ruta, "txt");
    }

    // Debe eliminar todos los ficheros dentro del directorio "dirBase" que tengan
    // la extensión dada
    public static void eliminar(Path dirBase, String extension) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.{" + extension + "}");
        try {
            Files.list(dirBase)
                    .filter(archivo -> Files.isRegularFile(archivo))
                    .filter(archivo -> matcher.matches(archivo)).forEach(archivo -> {
                        try {
                            Files.delete(archivo);
                            // java.awt.Desktop.getDesktop().moveToTrash(dirBase.toFile()); ENVIA A LA
                            // PAPELERA DE RECICLAJE
                        } catch (IOException e) {
                        }
                    });
        } catch (IOException e) {
        }
    }

    /*
     * Debe cambiar el nombre de los ficheros incluidos dentro del directorio
     * "dirBase"
     * (y sus subdirectorios). Al nombre de cada fichero se le concatenará (antes de
     * la extensión, si la hay) un número contando desde 1. Es decir, al primer
     * fichero encontrado se le
     * añadirá un 1, al segundo fichero un 2, etc.
     */
    public static void renombrar(Path dirBase) {
        try {
            Files.walkFileTree(dirBase, new SimpleFileVisitor<Path>() {
                int contador = 1;

                public String renombrar(String nombreArchivo, int contador) {
                    int index = nombreArchivo.lastIndexOf(".");
                    if (index < 0) {
                        return nombreArchivo.concat(String.valueOf(contador));
                    }
                    return nombreArchivo.substring(0, index).concat(String.valueOf(contador))
                            .concat(nombreArchivo.substring(index));
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String nuevoNombre = renombrar(file.getFileName().toString(), contador);
                    try {
                        file.toFile().renameTo(file.getParent().resolve(nuevoNombre).toFile());
                    } catch (RuntimeException e) {
                    }
                    contador++;
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     * Debe copiar los ficheros del directorio "dirOrigen", que tengan una de las
     * extensión dadas,
     * dentro del directorio "dirDestino". Al copiar se conservará el nombre y
     * extensión de los ficheros.
     */
    public static void copiar(Path dirOrigen, Path dirDestino, String... extensiones) {
        try {
            Files.walkFileTree(dirOrigen, new HashSet<>(), 1, new SimpleFileVisitor<Path>() {
                String extensionesJoin = Stream.of(extensiones).collect(Collectors.joining(","));
                PathMatcher buscaExt = FileSystems.getDefault().getPathMatcher("glob:**.{" + extensionesJoin + "}");

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (buscaExt.matches(file)) {
                        try {
                            Files.copy(file, dirDestino.resolve(file.getFileName()),
                                    StandardCopyOption.REPLACE_EXISTING);
                        } catch (RuntimeException e) {
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Debe explorar los subdirectorios dentro de "dirOrigen", mover todos los
     * ficheros a "dirOrigen" y
     * eliminar el subdirectorio. Al final el directorio "dirOrigen" debe contener
     * sólo los ficheros.
     * Si falla alguna operación en un subdirectorio y no puede eliminarse, debe
     * continuarse con el siguiente subdirectorio.
     */
    public static void aplanar(Path dirOrigen) {
        try {
            Files.walkFileTree(dirOrigen, new FileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    try {
                        Files.move(file, dirOrigen.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    try{
                        Files.delete(dir);
                    }catch(IOException e){ };
                    return FileVisitResult.CONTINUE;

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
