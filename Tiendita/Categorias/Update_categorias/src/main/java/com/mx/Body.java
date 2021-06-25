package com.mx;

public class Body {
    String categoriaId = "";
    String categoriaNombre ="";
    String categoria_descripcion = "";
    String categoria_activo = "";

    public Body(){
    }

    public Body(String categoriaId, String categoriaNombre, String categoria_descripcion, String categoria_activo){
        this.categoriaId = categoriaId;
        this.categoriaNombre = categoriaNombre;
        this.categoria_descripcion = categoria_descripcion;
        this. categoria_activo = categoria_activo;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getCategoria_descripcion() {
        return categoria_descripcion;
    }

    public void setCategoria_descripcion(String categoria_descripcion) {
        this.categoria_descripcion = categoria_descripcion;
    }

    public String getCategoria_activo() {
        return categoria_activo;
    }

    public void setCategoria_activo(String categoria_activo) {
        this.categoria_activo = categoria_activo;
    }

}
