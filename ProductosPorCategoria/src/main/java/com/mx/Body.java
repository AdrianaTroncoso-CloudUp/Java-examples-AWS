package com.mx;

public class Body {
    int idProductosPerCategoria = 0;

    public Body(){
    }

    public Body(int idProductosPerCategoria){

        this.idProductosPerCategoria = idProductosPerCategoria;
    }

    public int getIdProductosPerCategoria(){

        return idProductosPerCategoria;
    }

    public void setIdProductosPerCategoria(int idProductosPerCategoria){

        this.idProductosPerCategoria = idProductosPerCategoria;
    }

}
