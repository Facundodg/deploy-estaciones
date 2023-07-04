package com.dim.dominio.enumeracion;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TipoBusqueda {
    @JsonProperty("Cusi")
    CUSI("Cusi"),

    @JsonProperty("Usuario")
    USUARIO("Usuario"),

    @JsonProperty("Puerto")
    PUERTO("Puerto");

    private final String nombre;

    TipoBusqueda(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;

    }
}
