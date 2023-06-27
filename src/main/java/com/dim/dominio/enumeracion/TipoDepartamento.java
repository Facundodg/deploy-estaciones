package com.dim.dominio.enumeracion;

public enum TipoDepartamento {
    ACTIVIDAD_ECONOMICA(" Actividad Económica"),
    SUBDIRECCION_INFORMATICA("Subdirección Informática"),
    LIBRE_DEUDA ("Libre Deuda"),
    CESION_HABERES("Cesión de Haberes"),
    OTROS_TRIBUTOS("Otros Tributos"),
    JURIDICO("JURídico"),
    CONTROL_BANCARIO("Control Bancario"),
    ADMINISTRATIVA("Administrativa"),
    PERSONAL("PERsonal"),
    FISCALIZACION("Fizcalización");

    private final String nombre;

    TipoDepartamento(final String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;

    }

}
