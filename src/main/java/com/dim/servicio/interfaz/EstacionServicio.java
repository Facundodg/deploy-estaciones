package com.dim.servicio.interfaz;

import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.Estacion;

import java.util.Collection;

public interface EstacionServicio extends ServicioCrud<Estacion> {

    Collection<EstacionPropiedades> buscarTodosConPropiedades();

    Collection<EstacionPropiedades> buscarEstacionesSimplificadas();

    boolean existePorPuerto(Long puerto);

    boolean eliminarPorPuerto(Long puerto);
}
