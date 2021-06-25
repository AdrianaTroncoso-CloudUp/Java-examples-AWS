package com.mx;

public class Body {
//    int idUser = 0;
    String nombre = "";
    String apaterno ="";
    String amaterno = "";
    String fecha_nacimiento = "";
    String sexo;

    public Body(){
    }

    public Body(String nombre, String apaterno, String amaterno, String fecha_nacimiento, String sexo){
        this.nombre = nombre;
        this.apaterno = apaterno;
        this.amaterno = amaterno;
        this. fecha_nacimiento = fecha_nacimiento;
        this.sexo =sexo;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getApaterno(){
        return apaterno;
    }

    public void setApaterno(String apaterno){
        this.apaterno = apaterno;
    }

    public String getAmaterno(){
        return amaterno;
    }

    public void setAmaterno(String amaterno) {
        this.amaterno = amaterno;
    }

    public String getFecha_nacimiento(){
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


}
