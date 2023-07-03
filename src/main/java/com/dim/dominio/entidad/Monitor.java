package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "monitor")
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_monitor", nullable = false)
    private Long idMonitor;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "numero_serie")
    private int numeroSerie;

    @Column(name = "marca")
    private String marca;

    @OneToOne(mappedBy = "monitor", fetch = FetchType.EAGER)
    @JoinTable(name = "estacion",/*inverseJoinColumns = {@JoinColumn(name = "id_estacion")}, */foreignKey = @ForeignKey(name = "id_estacion"))
    private Estacion estacion;

}
