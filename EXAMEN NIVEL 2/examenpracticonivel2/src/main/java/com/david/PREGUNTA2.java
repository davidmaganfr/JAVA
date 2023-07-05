package com.david;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//PROGRAMACION ASINCRONA
/* La siguiente clase Crono se utilizará para crear un cronómetro que obtendrá la hora actual 
del sistema cada 100 milisegundos.
Añade el código necesario al método inicia() para que lance un proceso asíncrono que cada 100 milisegundos 
actualice el campo horaActual con la hora actual del sistema. Añade el código necesario al método para() para 
que finalice la actualización asíncrona de la variable horaActual. El método inicia() debe comprobar que aún 
no se está actualizando la hora (porque si no no hará nada), y el método para() debe comprobar que se está 
actualizando la hora (porque si no no hará nada).*/

public class PREGUNTA2 {
    public static void main(String[] args) throws InterruptedException {
        Crono cronometro = new Crono();
        cronometro.inicia();
        Thread.sleep(3000);
        cronometro.para();
    }
}

class Crono extends Thread {
    private LocalTime horaActual;

    public void inicia() {
        if (!this.isAlive()) {
            this.start();
        }
    }
    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                horaActual = LocalTime.now();
                Thread.sleep(100);
                System.out.println(getHoraActual().format(DateTimeFormatter.ofPattern("hh:mm:ss:ms")));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    public void para() {
        if (!this.isInterrupted()) {
            this.interrupt();
        }
    }

    public LocalTime getHoraActual() {
        return horaActual;
    }
}