package com.dim.dominio.enumeracion;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Busqueda {
    @JsonProperty("Cusi")
    CUSI("Cusi"),

    @JsonProperty("Usuario")
    USUARIO("Usuario"),

    @JsonProperty("Estación")
    ESTACION("Estacion"),

    @JsonProperty("Puerto")
    PUERTO("Puerto");

    private final String nombre;

    Busqueda(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;

    }
}
