package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cusi")
public class Cusi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_cusi")
    private Long id;

    @Column(name = "num_cusi")
    private Long numCusi;

    @Column(name = "micro")
    private String micro;

    @Column(name = "mother")
    private String mother;

    @Column(name = "ram")
    private String ram;

    @Column(name = "disco")
    private String disco;

    @Column(name = "so")
    private String so;

    @Column(name = "mouse")
    private boolean mouse;

    @Column(name = "teclado")
    private boolean teclado;

    @Column(name = "dvd")
    private boolean dvd;

    @OneToOne(fetch = FetchType.LAZY)
    private Estacion estacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cusi cusi)) return false;

        return getId().equals(cusi.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
