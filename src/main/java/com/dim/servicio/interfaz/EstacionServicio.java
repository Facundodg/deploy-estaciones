package com.dim.servicio.interfaz;

import com.dim.dominio.dto.estacion.EstacionAltaModificar;
import com.dim.dominio.dto.estacion.EstacionPropiedades;
import com.dim.dominio.dto.general.ConjuntoAlta;
import com.dim.dominio.dto.general.ConjuntoAltaModificar;
import com.dim.dominio.entidad.Cusi;
import com.dim.dominio.entidad.Estacion;
import com.dim.dominio.entidad.Monitor;

import java.util.Collection;

public interface EstacionServicio extends ServicioCrud<Estacion> {

    Collection<EstacionPropiedades> buscarTodosConPropiedades();

    Collection<EstacionPropiedades> buscarEstacionesSimplificadas();

    boolean existePorPuerto(Long puerto);

    boolean eliminarPorPuerto(Long puerto);

    boolean modificarPorPuerto(Estacion estacion, Cusi cusi, Monitor monitor);

    //Object modificarPorPuerto(long puerto, ConjuntoAlta conjuntoAltaModificar);
}
