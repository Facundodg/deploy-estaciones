package com.dim.servicio.interfaz;

import com.dim.dominio.dto.EstacionDto;
import com.dim.dominio.entidad.Estacion;

import java.util.Collection;

public interface EstacionServicio extends ServicioCrud<Estacion> {

    Collection<EstacionDto> buscarDatosGenerales();
}
