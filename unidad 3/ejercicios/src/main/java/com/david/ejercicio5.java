package com.david;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class ejercicio5 {
    public static void main(String[] args) throws RuntimeException, ParseException {
        Locale[] idioma = { Locale.getDefault(), Locale.ENGLISH, Locale.US, Locale.UK };


        // System.out.println(Arrays.toString(formatosNumero(3231, 2, idioma)));
        // System.out.println(Arrays.toString(separaInfoPersonaSplit("David;33
        // años-telefono 675914852/Nacido en España")) );
        // System.out.println(Arrays.toString(separaInfoPersonaStringTokenizer("David;33
        // años-telefono 675914852/Nacido en España")) );
        // System.out.println(separaInfoPersonaPattern("David;33 años-telefono
        // 675914852/Nacido en España"));

        // System.out.println(FormatoValido.CORTO.formato);
        // System.out.println("yyyy/MMMM/dd");
        System.out.println(convierteFecha("1989-10-12 12:34:00", FormatoValido.LARGO));

    }
    // Los argumentos son:
    // numero: un número con o sin decimales al que queremos darle formato.

    // redondeo: a cuántos dígitos decimales se debe redondear el primer argumento.
    // Por ejemplo, si "numero=1234.678" y "redondeo=2", se debe obtener el valor
    // "1234.68". Si "numero=67" y "redondeo=2",
    // se debe obtener el valor "67.00".

    // idiomas: es un array de objetos Locale. Cada Locale representa un país para
    // el que queremos dar formato al número.

    // Este método debe retornar un array de strings conteniendo la representación
    // del número para cada uno de los países incluyendo
    // separadores de miles y separadores de decimales. (Para resolver este método
    // puedes usar la clase Formatter o la clase NumberFormat.)
    public static String[] formatosNumero(double numero, int redondeo, Locale[] idiomas) {
        String[] listResult = new String[idiomas.length];
        for (int i = 0; i < idiomas.length; i++) {
            listResult[i] = String.format(idiomas[i], "%,." + redondeo + "f", numero);
        }
        return listResult;
    }

    public static String[] separaInfoPersonaSplit(String info) {
        String[] split = info.split("\\p{Punct}");
        return split;
    }

    public static String[] separaInfoPersonaStringTokenizer(String info) {
        StringTokenizer st = new StringTokenizer(info, ";-/");
        String[] arrayStrings = new String[st.countTokens()];
        int index = 0;
        while (st.hasMoreTokens()) {
            arrayStrings[index] = st.nextToken();
            index++;
        }
        return arrayStrings;
    }

    public static List<String> separaInfoPersonaPattern(String info) {
        var arrayPattern = Pattern.compile("\\p{Punct}")
                .splitAsStream(info)
                .map(wr -> wr.toString())
                .toList();
        return arrayPattern;
    }

    public static Date convierteFecha(String fecha, FormatoValido formato) {
        SimpleDateFormat formatter = new SimpleDateFormat(formato.format, Locale.getDefault());
        try {
            return formatter.parse(fecha);
        } catch (ParseException e) {
            return null;
        }
    }

    enum FormatoValido {
        CORTO("yyyy-MMMM-dd"),
        MEDIO("yyyy-MM-dd-HH"),
        LARGO("yyyy-MM-dd HH:mm:ss");

        private String format;

        private FormatoValido(String format) {
            this.format = format;
        }
    }
}
