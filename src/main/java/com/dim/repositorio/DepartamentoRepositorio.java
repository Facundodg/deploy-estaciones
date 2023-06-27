package com.dim.repositorio;

import com.dim.dominio.entidad.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
}
