package com.dim.entidad;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cusi")
public class Cusi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String micro;
    private String mother;
    private String ram;
    private String disco;
    private String so;
    private boolean mouse;
    private boolean teclado;
    private boolean dvd;



}
