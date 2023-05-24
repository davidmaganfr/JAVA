package com.david;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.GregorianCalendar;


/* Solicita un año, un mes y un día, y resuelve las siguientes cuestiones:

Comprueba que forma una fecha válida.
Obtén el nombre del día de la semana correspondiente a la fecha.
Indica si es un año bisiesto.
Muestra un texto con la fecha en el formato: "lunes 2 de marzo de 2020" */

public class ejercicio3 {
    public static void main(String[] args) {
        System.out.println("Introduce una fecha en formato año-mes-dia: ");
        String dateString = System.console().readLine();
        System.out.println(isValidDate(dateString)); 
        LocalDate date = changeToLocalDate(dateString);
        System.out.println(date);
        System.out.println(nameDayOfWeek(date)); 
        System.out.println(isLeap(date)); 
        System.out.println(changeFormat(date)); 
    
    }

    private static boolean isValidDate(String date) {
        try{changeToLocalDate(date);
        } catch(Exception e){
            return false;
        }
        return true;
    }

    private static String changeFormat(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        return formatter.format(date);
    }

    private static boolean isLeap(LocalDate date) {
        int year = date.getYear();
        GregorianCalendar calendar = new GregorianCalendar();
        return calendar.isLeapYear(year);
    }
    private static String nameDayOfWeek(LocalDate date) {
        return date.getDayOfWeek().toString();
    }
    private static LocalDate changeToLocalDate(String date) {
        LocalDate formatterToDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-M-d"));
        return formatterToDate;
    }

}
