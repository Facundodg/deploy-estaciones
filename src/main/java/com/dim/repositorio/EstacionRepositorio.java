package com.dim.repositorio;

import com.dim.entidad.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepositorio extends JpaRepository<Estacion, Long> {
}
