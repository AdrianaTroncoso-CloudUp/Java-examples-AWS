package com.mx;

public class Body {
//    int idProducto = 0;
    String nombre = "";
    String descripcion ="";
    String precio = "";
    String idCategoria = "";

    public Body(){
    }

    public Body(String nombre, String apaterno, String amaterno, String fecha_nacimiento, String sexo){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this. idCategoria = idCategoria;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getPrecio(){
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getIdCategoria() { return idCategoria; }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

}
