package main;

import java.util.Date;

public class Movimiento {
    //Variables
    private final String nombre;
    private final Date fecha;
    private final float valor;
    //Constructor
    public Movimiento(String nombre, Date fecha, float valor){
        this.nombre = nombre;
        this.fecha = fecha;
        this.valor = valor;
    }
    //Métodos//
    //Getters
    public String getNombre(){return nombre;}
    public Date getFecha(){return fecha;}
    public float getValor(){return valor;}
}
