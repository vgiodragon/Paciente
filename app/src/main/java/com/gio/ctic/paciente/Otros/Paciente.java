package com.gio.ctic.paciente.Otros;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by giovanny on 09/06/16.
 */
public class Paciente implements Parcelable {
    private String nombre;
    private String dni;
    private String id;

    public Paciente(String nombre, String dni, String id) {
        this.nombre = nombre;
        this.dni = dni;
        this.id = id;
    }

    protected Paciente(Parcel in) {
        nombre = in.readString();
        dni = in.readString();
        id = in.readString();
    }

    public static final Creator<Paciente> CREATOR = new Creator<Paciente>() {
        @Override
        public Paciente createFromParcel(Parcel in) {
            return new Paciente(in);
        }

        @Override
        public Paciente[] newArray(int size) {
            return new Paciente[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(dni);
        dest.writeString(id);
    }
}
