package main;

import java.util.Date;

public class Balance {
    //Variables
    private Date fInicio;
    private Date fFinal;
    private float resultado;
    private Usuario usuario;
    //Constructor
    public Balance(Date fInicio,Date fFinal, float resultado, Usuario usuario){
        this.fInicio = fInicio;
        this.fFinal = fFinal;
        this.resultado = resultado;
        this.usuario = usuario;
    }
}
