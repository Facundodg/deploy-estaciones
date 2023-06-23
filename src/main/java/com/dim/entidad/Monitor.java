package com.dim.entidad;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "monitor")
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_monitor;
    private String modelo;
    private int numeroserie;
    private String marca;

}
