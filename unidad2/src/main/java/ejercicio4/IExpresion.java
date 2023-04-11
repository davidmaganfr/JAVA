package ejercicio4;

interface IExpresion {
    double getValor();
    default String getEcuacion(){
        return toString() + " = " + getValor();
    }
}
