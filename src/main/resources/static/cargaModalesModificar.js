const estacion = {

    "departamento": "",
    "estacion": {
        "puerto": ""
    },
    "cusi": {
        "disco": "",
        "micro": "",
        "mother": "",
        "so": "",
        "ram": "",
        "hostName": "",
        "mac": "",
        "ip": "",
        "dvd": "",
        "mouse": "",
        "teclado": "",
        "num_cusi": ""
    },
    "monitor": {
        "marca": "",
        "modelo": "",
        "numero_serie": ""
    }

}

function cargarDataJsonEstacionModificar(){

    var disco = document.getElementById('imput_disco_modificar');
    estacion.cusi.disco = disco.value;

}

/*

    "usuario": [



    ]


*/