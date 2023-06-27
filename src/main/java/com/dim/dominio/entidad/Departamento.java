package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "departamento")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_departamento", nullable = false)
    private Long idDepartamento;

    @Column(name = "nombre_depto")
    private String nombreDepartamento;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento that)) return false;

        return getIdDepartamento().equals(that.getIdDepartamento());
    }

    @Override
    public int hashCode() {
        return getIdDepartamento().hashCode();
    }
}
