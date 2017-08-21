package main;

public class Producto {
    //Variables
    private int id;
    private float precio;
    private String nombre;
    //Constructor
    public Producto(int id,float precio,String nombre){
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
    }
    public int getId(){return id;}
    public float getPrecio(){return precio;}
    public String getNombre(){return nombre;}
}
