package main;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class NRomano {
    private int numero;
    static List<String> listNRomanos = new ArrayList<String>();
    static { 
        for(int i=1; i<=3998; i++){
        listNRomanos.add(obtenerRomano(i));
        }
    }

    public NRomano(int numero) {
        this.numero = numero;
    }
    public NRomano(String nRomano) {
        this.numero = listNRomanos.indexOf(nRomano);
    }
    public static String obtenerRomano(int numero) {
        String num = "";
        int i, miles, centenas, decenas, unidades;

        miles = numero/1000;
        centenas = numero / 100 % 10;
        decenas = numero / 10%10;
        unidades = numero % 10;

        //miles
        for(i = 1; i <= miles; i++){
            num += "M";
        }
        //centenas
        if(centenas == 9){
            num += "CM";
        } else if(centenas >= 5){
            num += "D";
            for(i = 6; i <=centenas; i++){
                num += "C";
            }
        } else if(centenas == 4){
            num += "CD";
        } else {
            for(i=1; i <=centenas; i++){
                num += "C";
            }
        }
        //decenas
        if(decenas == 9){
            num += "XC";
        } else if(decenas >= 5){
            num += "L";
            for(i = 6; i <=decenas; i++){
                num += "X";
            }
        } else if(decenas == 4){
            num += "XL";
        } else {
            for(i=1; i <=decenas; i++){
                num += "X";
            }
        }
        //unidades
        if(unidades == 9){
            num += "IX";
        } else if(unidades >= 5){
            num += "V";
            for(i = 6; i <=unidades; i++){
                num += "I";
            }
        } else if(unidades == 4){
            num += "IV";
        } else {
            for(i=1; i <=unidades; i++){
                num += "I";
            }
        }
        return num;
    }

    @Override
    public String toString() {
        return "Numero romano: " + obtenerRomano(numero);
    }
    public NRomano add(NRomano romano){
        int index = romano.numero + this.numero;
        return new NRomano(listNRomanos.get(index - 1)); 
    }

}

