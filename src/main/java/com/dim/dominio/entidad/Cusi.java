package com.dim.dominio.entidad;

import jakarta.persistence.*;
import lombok.*;

import java.net.InetAddress;

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

    @Column(name = "ip")
    private InetAddress ip;

    @Column(name = "mac")
    private String mac;

    @Column(name = "hostname")
    private String hostName;

//    @OneToOne(mappedBy = "cusi", fetch = FetchType.EAGER)
//    @JoinTable(name = "estacion", inverseJoinColumns = {@JoinColumn(name="id_estacion")})
//    private Estacion estacion;

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


/*


{
    "departamento": "1",
    "estacion": {
        "id_estacion": "1",
        "puerto": "12345"
    },
    "cusi": {
        "id_cusi": "1",
        "disco": "dasdad",
        "micro": "micro",
        "mother": "dasdasdsa",
        "so": "dasdsadsa",
        "ram": "dsadasdsa",
        "hostName": "dasdsadsadsa",
        "mac": "NO DATOS",
        "ip": "123123123",
        "dvd": false,
        "mouse": true,
        "teclado": true
    },
    "monitor": {
        "id_monitor": "1",
        "marca": "sadasd",
        "modelo": "sadasd",
        "numero_serie": "12312312"
    },
    "usuario": []
}


*/