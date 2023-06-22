package com.david;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*1. Crea una clase GestionAccesosCSV que utilice fichero CSV para almacenar los datos de acceso. 
En cada línea del fichero se debe insertar el nombre de usuario, la fecha y hora de acceso, separados por comas.
 Algo como:  Juan,2022-03-12,08:12:45
2. Crea una clase GestonAccesosXML que utilice un fichero en formato XML para almacenar los datos de acceso. 
3. Crea una clase GestonAccesosJson que utilice un fichero en formato Json para almacenar los datos de acceso. 
4. Crea una clase GestonAccesosBinary que utilice un fichero binario para almacenar los datos de acceso. 
(Usa serialización binaria con ObjectOutputStream y ObjectInputStream). 
 */
public class Ejercicio3 {
    public static void main(String[] args) throws IOException {
        GestionAccesosJson acceso = new GestionAccesosJson();
        System.out.println(acceso.getFichero());
        acceso.insertarAcceso("David", LocalDateTime.now());
        System.out.println(acceso.consultarUsuarios(LocalDate.of(2023, 6, 21)));
    }
}

class GestionAccesosCSV implements GestionAccesos, Serializable {
    private final Path ruta = Path.of(
            "C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\JAVA\\unidad 6\\operacionesconarchivos\\src\\main\\resources\\accesos.csv");

    @Override
    public Path getFichero() {
        return ruta;
    }

    @Override
    public void insertarAcceso(String usuario, LocalDateTime acceso) throws IOException {
        CSVFormat format = CSVFormat.EXCEL.builder()
                .setIgnoreEmptyLines(true)
                .setHeader("nombre", "fecha", "hora")
                .build();
        try (CSVPrinter printer = new CSVPrinter(
                Files.newBufferedWriter(ruta, StandardOpenOption.APPEND), format)) {
            printer.printRecord(usuario, acceso.toLocalDate(),
                    acceso.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        }
    }

    @Override
    public List<String> consultarUsuarios(LocalDate dia) throws IOException {
        try {
            return Files.lines(ruta)
                    .skip(1)
                    .filter(item -> comprobarFecha(item, dia))
                    .toList();
        } catch (IOException e) {
        }
        return null;
    }

    public boolean comprobarFecha(String item, LocalDate dia) {
        return LocalDate.parse(item.split(",")[1]).isEqual(dia);

    }

}

class GestionAccesosXML implements GestionAccesos {
    private final Path ruta = Path.of(
            "C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\JAVA\\unidad 6\\operacionesconarchivos\\src\\main\\resources\\accesos.xml");

    @Override
    public Path getFichero() {
        return ruta;
    }

    @Override
    public void insertarAcceso(String usuario, LocalDateTime acceso) throws IOException {
        List<Acceso> accesos = null;
        try (XMLDecoder decoder = new XMLDecoder(Files.newInputStream(ruta))) {
            Object resultado = decoder.readObject();
            accesos = (List<Acceso>) resultado;
        } catch (Exception e) {
            accesos = new ArrayList<Acceso>();
        }
        accesos.add(new Acceso(usuario, acceso.toLocalDate(), acceso.toLocalTime()));
        try (XMLEncoder encoder = new XMLEncoder(Files.newOutputStream(ruta))) {
            encoder.writeObject(accesos);
        }
    }

    @Override
    public List<String> consultarUsuarios(LocalDate dia) throws IOException {
        try (XMLDecoder decoder = new XMLDecoder(Files.newInputStream(ruta))) {
            List<Acceso> accesos = (List<Acceso>) decoder.readObject();
            return accesos.stream()
                    .filter(acc -> acc.getFecha().equals(dia.toString()))
                    .map(Acceso::getUsuario)
                    .toList();
        }
    }

}

class GestionAccesosJson implements GestionAccesos, Serializable {
    private final Path ruta = Path.of(
            "C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\JAVA\\unidad 6\\operacionesconarchivos\\src\\main\\resources\\accesos.json");

    @Override
    public Path getFichero() {
        return ruta;
    }

    @Override
    public void insertarAcceso(String usuario, LocalDateTime acceso) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try (var out = Files.newBufferedWriter(ruta, StandardOpenOption.APPEND)) {
            String json = mapper.writeValueAsString(new Acceso(usuario,
                    acceso.toLocalDate(),
                    acceso.toLocalTime()));
            out.append(json);
            out.newLine();
        }
    }

    @Override
    public List<String> consultarUsuarios(LocalDate dia) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return Files.lines(ruta).map(obj -> {
            try {
                return mapper.readValue(obj, Acceso.class);
            } catch (JsonProcessingException e) {
                return null;
            }
        })
                .filter(obj -> obj.getFecha().isEqual(dia))
                .map(obj -> obj.getUsuario())
                .toList();
    }
}

class GestionAccesosBinary implements GestionAccesos {
    /*
     * Con serializacion binaria podemos serializar accesos al final del fichero,
     * pero hay que eliminar las cabeceras que mete ObjectOutputStream y lee
     * ObjectInputStream.
     */
    private final Path ruta = Path.of(
            "C:\\Users\\rendi\\Documents\\GitHub\\CURSO JAVA\\FORMACION JAVA\\JAVA\\unidad 6\\operacionesconarchivos\\src\\main\\resources\\accesos.bin");
    private final ObjectMapper mapper = new ObjectMapper();

    public static class MyObjectOutputStream extends ObjectOutputStream {

        public MyObjectOutputStream(OutputStream out) throws IOException {// para no incluir cabeceras en el fichero
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }

    }

    public static class MyObjectInputStream extends ObjectInputStream {

        public MyObjectInputStream(InputStream in) throws IOException {
            super(in);
        }

        @Override
        protected void readStreamHeader() throws IOException, StreamCorruptedException {
        }
    }

    @Override
    public Path getFichero() {
        return ruta;
    }

    @Override
    public void insertarAcceso(String usuario, LocalDateTime acceso) throws IOException {
        try (ObjectOutputStream out = new MyObjectOutputStream(
                Files.newOutputStream(ruta, StandardOpenOption.APPEND))) {
            out.writeObject(new Acceso(usuario, acceso.toLocalDate(), acceso.toLocalTime()));
        }
    }

    @Override
    public List<String> consultarUsuarios(LocalDate dia) throws IOException {
        List<String> usuarios = new ArrayList<>();
        try (ObjectInputStream out = new MyObjectInputStream(Files.newInputStream(ruta))) {
            while (true) {
                try {
                    Acceso acceso = (Acceso) out.readObject();
                    if (acceso.getFecha().equals(dia.toString())) {
                        usuarios.add(acceso.getUsuario());
                    }
                } catch (EOFException | ClassNotFoundException e) {
                    break;
                }
            }
        }
        return usuarios;
    }

}

interface GestionAccesos {
    // Debe retornar la ruta del fichero usado para almacenar los accesos.
    Path getFichero();

    // Debe insertar un acceso al final del fichero.
    void insertarAcceso(String usuario, LocalDateTime acceso) throws IOException;

    // Debe obtener los nombres de usuario que accedieron en una día concreto
    List<String> consultarUsuarios(LocalDate dia) throws IOException;
}