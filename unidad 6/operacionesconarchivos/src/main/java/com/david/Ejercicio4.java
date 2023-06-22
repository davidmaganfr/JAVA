package com.david;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
Crea la siguiente clase Cita:
- Completa el método serializa() para que pueda serializarse el objeto actual a través del 
  stream destino. Completa el método estático deserializa() para que pueda recuperarse, a
  través del canal origen, un objeto Cita serializado.
- Cuando se serialice una cita sólo se podrán almacenar los 100 primeros caracteres del texto.
- Cuando se deserialice una cita hay que normalizar la fecha dejando los minutos y segundos a cero.
 */

public class Ejercicio4 {

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Cita implements Serializable {
    private LocalDateTime fecha;
    private String texto;
    

    public void serializa(OutputStream destino) {
        // ... código de serialización del objeto "this"
        try (var out = new ObjectOutputStream(destino)) {
            out.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Cita deserializa(InputStream origen) {
        // ... código para deserializar un Alquiler y retornarlo
        try (var input = new ObjectInputStream(origen)) {
            return (Cita) input.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeUTF(texto == null ? null : texto.substring(0, Math.min(100, texto.length())));
        out.writeObject(fecha);
    }

    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        this.texto = input.readUTF();
        this.fecha = (LocalDateTime) input.readObject();
        if (this.fecha != null)
            this.fecha = this.fecha.toLocalDate().atTime(0, 0);
    }
}
