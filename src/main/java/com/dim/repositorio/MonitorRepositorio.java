package com.dim.repositorio;

import com.dim.dominio.entidad.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepositorio extends JpaRepository<Monitor, Long> {
}
