package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estacion")
public class Estacion {

    @Id
    @Column(name = "id_estacion", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStacion;

    @Column(name = "puerto")
    private Long puerto;

    @JoinColumn(name = "id_cusi")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cusi cusi;

    @JoinColumn(name = "id_monitor")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Monitor monitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "estacion_usuario",
            joinColumns = {@JoinColumn(name = "id_estacion")},
            inverseJoinColumns = {@JoinColumn(name = "id_usuario")})
    private Set<Usuario> usuarios;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estacion estacion = (Estacion) o;
        return idStacion.equals(estacion.idStacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStacion);
    }
}
