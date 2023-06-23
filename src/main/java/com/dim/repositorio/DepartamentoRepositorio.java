package com.dim.repositorio;

import com.dim.entidad.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepositorio extends JpaRepository<Departamento, Long> {
}
