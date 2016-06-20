package com.gio.ctic.paciente.History;

/**
 * Created by user on 07/06/2016.
 */
public class Historial {
    private String pac;
    private String sintomas;
    private String descripcion;
    private String doctor;
    private String especialidad;

    public Historial(String pac, String doctor, String especialidad,String sintomas, String descripcion) {
        this.pac = pac;
        this.sintomas = sintomas;
        this.descripcion = descripcion;
        this.doctor = doctor;
        this.especialidad=especialidad;

    }
    public String getEspecialidad() {
        return especialidad;
    }

    public String getFecha() {
        return pac;
    }

    public String getSintomas() {
        return sintomas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDoctor() {
        return doctor;
    }
}
