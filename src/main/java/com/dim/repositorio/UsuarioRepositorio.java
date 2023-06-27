package com.dim.repositorio;

import com.dim.dominio.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    @Query(value = "Select * from public.usuario where numafiliado= :numafiliado" , nativeQuery = true)
    List<Usuario> finByNumeroAfiliado(long numafiliado); //prueba
}
