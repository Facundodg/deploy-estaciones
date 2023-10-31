package com.dim.repositorio;

import com.dim.dominio.entidad.Cusi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CusiRepositorio extends JpaRepository<Cusi, Long> {
}