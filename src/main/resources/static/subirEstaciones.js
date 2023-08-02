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
    },
    "usuario": [



    ]

}


/*

{

    "departamento": "2",
    "estacion": {
      "puerto": "12345"
    },
    "cusi": {
      "disco": "dasdas",
      "micro": "dasdasd",
      "mother": "asdasdas",
      "so": "dasdas",
      "ram": "dasdsa",
      "hostName": "dasdsa",
      "mac": "dasdas",
      "ip": "198.24.10.0/24",
      "dvd": true,
      "mouse": true,
      "teclado": false,
      "num_cusi":"1234",
      "mac":"123.123.123"
    },
    "monitor": {
      "marca": "dasdas",
      "modelo": "asdasd",
      "numero_serie": "123456"
    },
    "usuario": [
      {
        "cuenta": "dasdasd",
        "clave": "dasdasd",
        "num_afiliado":43221
      }
    ]

}


*/



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

        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })

}


function subirMonitor() {

    var marca = document.getElementById('marcaInput');
    var modelo = document.getElementById("modeloInput");
    var numserie = document.getElementById("numserieInput");

    estacion.monitor.marca = marca.value;
    estacion.monitor.modelo = modelo.value;
    estacion.monitor.numero_serie = numserie.value;


    console.log(estacion);

        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })

}

function subirUsuario() {

    var usuario = document.getElementById('usuarioInput');
    var contraseña = document.getElementById("contraseñaInput");
    var num_afiliado = document.getElementById("numAfiliadoInput");


    //numAfiliadoInput

    usuario = {

        "usuario": usuario.value,
        "clave": contraseña.value,
        "num_afiliado":num_afiliado.value

    }

    estacion.usuario.push(usuario);

    console.log(estacion);

        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })

}


var depto = document.getElementById('selector');

depto.addEventListener('change', function () {

    console.log('El valor seleccionado ha cambiado:', depto.value);

    estacion.departamento = depto.value;

});

async function subirEstaciones() {

    var puertoCusInput = document.getElementById('estacionInput');
    estacion.estacion.puerto = puertoCusInput.value;


    var numcusi = document.getElementById('cusiInput');

    estacion.cusi.num_cusi = numcusi.value;

    estacion.departamento = depto.value;

    //cusiInput

    let headersList = {
        'Accept': 'application/json',
        "User-Agent": "Thunder Client (https://www.thunderclient.com/)",
        "Content-Type": "application/json"
    }

    let request = await fetch("http://172.20.255.23:4040/main", {
        method: "POST",
        body: JSON.stringify(estacion),
        headers: headersList
    });

    const estaciones = await request.json();

    console.log(estacion)

    Swal.fire({
      position: 'top-end',
      icon: 'success',
      title: 'Your work has been saved',
      showConfirmButton: false,
      timer: 1500
    })

    setTimeout(refresco, 1000);

}

function refresco() {
    location.reload();
}
