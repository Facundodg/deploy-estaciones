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

function removerChildNodes(parent){

    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }

}

var depto = document.getElementById('selector');

depto.addEventListener('change', function () {

    console.log('El valor seleccionado ha cambiado:', depto.value);

    estacion.departamento = depto.value;

});

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

    renderBotonesDataMonitoUsuario();


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

    var nombre = document.getElementById('nombreInput');
    var apellido = document.getElementById("apellidoInput");
    var departamento = depto.value;

    usuario = {

        "usuario": usuario.value,
        "clave": contraseña.value,
        "num_afiliado":num_afiliado.value,
        "nombre":nombre.value,
        "apellido":apellido.value,
        "departamento":departamento

    }

    estacion.usuario.push(usuario);

    actualizarTablaUsuariosAgrgados();

    renderBotonesDataMonitoUsuario()

        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Your work has been saved',
          showConfirmButton: false,
          timer: 1500
        })

}

function actualizarTablaUsuariosAgrgados(){

    let usuarios = estacion.usuario;

    const body = document.querySelector(".tabla_usuarios");

    removerChildNodes(body)

    for (let usuario of usuarios) {

           renderTablaUsuarios(usuario)

    }

}

function renderTablaUsuarios(usuario){

      const body = document.querySelector(".tabla_usuarios");

      const row = document.createElement('tr')
      row.className = "bg-white border-b dark:bg-gray-800 dark:border-gray-700"

      const departamento = document.createElement('td')
      departamento.className = "px-6 py-4"
      departamento.setAttribute("scope", "row");
      departamento.textContent = usuario.departamento;

       const nombre = document.createElement('td')
       nombre.className = "px-6 py-4"
       nombre.setAttribute("scope", "row");
       nombre.textContent = usuario.nombre;

      const apellido = document.createElement('td')
      apellido.className = "px-6 py-4"
      apellido.setAttribute("scope", "row");
      apellido.textContent = usuario.apellido;

       const usuarioRow = document.createElement('td')
       usuarioRow.className = "px-6 py-4"
       usuarioRow.setAttribute("scope", "row");
       usuarioRow.textContent = usuario.usuario;


       const contraseña = document.createElement('td')
       contraseña.className = "px-6 py-4"
       contraseña.textContent = usuario.clave;

      const opciones = document.createElement('td')
      opciones.className = "flex justify-center items-center px-1 py-1 space-x-2 whitespace-nowrap mt-1"

      const botonEliminar = document.createElement('button')
      botonEliminar.setAttribute("href", "#modal"); // Reemplaza la URL con la dirección que desees
      botonEliminar.setAttribute("onclick",'eliminarUsuarioIngresoDeUsuario(`' + usuario.usuario + '`)')
      botonEliminar.className = "btn-options bg-red-500 hover:bg-red-800 focus:ring-4"
      botonEliminar.textContent = "Eliminar"


      row.append(departamento)
      row.append(nombre)
      row.append(apellido)
      row.append(usuarioRow)
      row.append(contraseña)
      row.append(opciones)
      opciones.append(botonEliminar)
      body.append(row)

}

function eliminarUsuarioIngresoDeUsuario(nombreUsuario){

    let posicionDeUsuario = buscarEnArray(nombreUsuario);

    console.log("posicionDeUsuario")
    console.log(posicionDeUsuario)
    console.log("posicionDeUsuario")
    console.log(estacion.usuario)
    console.log("estacion.usuario")

    estacion.usuario.splice(posicionDeUsuario, 1);

    console.log(estacion.usuario)

    const body = document.querySelector(".tabla_usuarios");

    removerChildNodes(body)

    let usuarios = estacion.usuario;

    for (let usuario of usuarios) {

           renderTablaUsuarios(usuario)

    }

    renderBotonesDataMonitoUsuario();

}

function buscarEnArray(nombreUsuario){

    return estacion.usuario.findIndex(function(estacion) {
         return estacion === nombreUsuario;
    });

}

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

function renderBotonesDataMonitoUsuario(){

    const propiedadesMonitor = Object.keys(estacion.monitor);

    console.log(propiedadesMonitor > 0)
    console.log(propiedadesMonitor)

    if(estacion.monitor.marca && estacion.monitor.marca && estacion.monitor.numero_serie !== ""){

        const boton_monitor = document.querySelector("#btn_agregar_monitor");
        boton_monitor.textContent = "Agregar Monitor " + "(add)"

    }

    if(estacion.usuario.length >= 1){

        const boton_usuario = document.querySelector("#btn_agregar_usuario");

        let numeUsuarios = (estacion.usuario.length).toString();

        console.log(numeUsuarios);

        boton_usuario.textContent = "Agregar Usuarios (" + numeUsuarios + ")";

    }else{

        const boton_usuario = document.querySelector("#btn_agregar_usuario");
        boton_usuario.textContent = "Agregar Usuarios";


    }

}

function refresco() {
    location.reload();
}
