package com.dim.repositorio;

import com.dim.entidad.Cusi;
import com.dim.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterfaseCusi extends JpaRepository<Cusi, Long> {
}