package com.dim.servicio.interfaz;

import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.entidad.Estacion;

import java.util.Collection;

public interface EstacionServicio extends ServicioCrud<Estacion> {

<<<<<<< HEAD
    Collection<EstacionPropiedades> buscarTodosConPropiedades();
=======
    Collection<EstacionPropiedades> buscarEstacionesSimplificadas();

    boolean eliminarPorPuerto(Long puerto);
>>>>>>> a773bbbfe2dd277c0b81e920e2a9c2f7c34f8c11
}
