package com.mx;

public class Body {
    int idProducto = 0;

    public Body(){
    }

    public Body(int idProducto){

        this.idProducto = idProducto;
    }

    public int getIdProducto(){

        return idProducto;
    }

    public void setIdProducto(int idProducto){

        this.idProducto = idProducto;
    }

}
