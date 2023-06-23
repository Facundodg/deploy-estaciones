package com.dim.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

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

    @Column(name = "id_departamento")
    private Long idDepartamento;

    @Column(name = "id_monitor")
    private Long idMonitor;

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
