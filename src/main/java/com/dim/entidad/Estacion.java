package com.dim.entidad;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "estacion")
public class Estacion {

    @Id
    @Column(name = "id_estacion", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStacion;

    @Column(name = "hostname")
    private String hostName;

    @Column(name = "mac")
    private String mac;

    @Column(name = "puerto")
    private Long puerto;

    @JoinColumn(name = "id_cusi")
    @OneToOne(fetch = FetchType.LAZY)
    private Cusi cusi;

    @JoinColumn(name = "id_monitor")
    @OneToOne(fetch = FetchType.LAZY)
    private Monitor monitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "estacion_usuario",
            joinColumns = {@JoinColumn(name = "id_usuario")},
            inverseJoinColumns = {@JoinColumn(name = "id_estacion")})
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
