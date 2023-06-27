package com.dim.repositorio;

import com.dim.dominio.entidad.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {
}
