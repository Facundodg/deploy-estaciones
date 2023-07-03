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
        "teclado": ""
    },
    "monitor": {
        "marca": "",
        "modelo": "",
        "numero_serie": ""
    },
    "usuario": []

}



function subirCusi() {

    var hostName = document.getElementById('hostInput');
    var macInput = document.getElementById("macInput");
    var discoInput = document.getElementById('discoInput');
    var microInput = document.getElementById("microInput");
    var motherInput = document.getElementById("motherInput");
    var ramInput = document.getElementById("ramInput");
    var soInput = document.getElementById("soInput");
    var dvdCheckBox = document.getElementById("dvdCheckBox");
    var mouseCheckBox = document.getElementById("mouseCheckBox");
    var tecladoCheckBox = document.getElementById("tecladoCheckBox");
    estacion.cusi.hostName = hostName.value;
    estacion.cusi.mac = macInput.value;
    estacion.cusi.disco = discoInput.value;
    estacion.cusi.micro = microInput.value;
    estacion.cusi.mother = motherInput.value;
    estacion.cusi.ram = ramInput.value;
    estacion.cusi.so = soInput.value;
    estacion.cusi.dvd = dvdCheckBox.checked;
    estacion.cusi.mouse = mouseCheckBox.checked;
    estacion.cusi.teclado = tecladoCheckBox.checked;

    console.log(estacion);

}


function subirMonitor() {

    var marca = document.getElementById('marcaInput');
    var modelo = document.getElementById("modeloInput");
    var numserie = document.getElementById("numserieInput");

    estacion.monitor.marca = marca.value;
    estacion.monitor.modelo = modelo.value;
    estacion.monitor.numero_serie = numserie.value;


    console.log(estacion);

}

function subirUsuario() {

    var usuario = document.getElementById('usuarioInput');
    var contraseña = document.getElementById("contraseñaInput");

    usuario = {

        "usuario": usuario.value,
        "contraseña": contraseña.value

    }

    estacion.usuario.push(usuario);

    console.log(estacion);

}


var depto = document.getElementById('selector');

depto.addEventListener('change', function () {

    console.log('El valor seleccionado ha cambiado:', depto.value);

    estacion.departamento = depto.value;

});


async function subirEstaciones() {

    var puertoCusInput = document.getElementById('estacionInput');
    estacion.estacion.puerto = puertoCusInput.value;

    estacion.departamento = depto.value;

    let headersList = {
        'Accept': 'application/json',
        "User-Agent": "Thunder Client (https://www.thunderclient.com)",
        "Content-Type": "application/json"
    }

    let request = await fetch("http://localhost:4040/main", {
        method: "POST",
        body: JSON.stringify({ estacion }),
        headers: headersList
    });

    const estaciones = await request.json();

    console.log(estacion)

}