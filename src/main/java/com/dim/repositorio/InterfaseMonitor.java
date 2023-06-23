package com.dim.repositorio;

import com.dim.entidad.Monitor;
import com.dim.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaseMonitor extends JpaRepository<Monitor, Long> {
}
