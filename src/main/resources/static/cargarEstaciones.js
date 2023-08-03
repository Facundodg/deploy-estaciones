async function cargarEstaciones(estacion) {

    const body = document.querySelector(".tablita");
    
    const row = document.createElement('tr')
    row.className = "bg-white border-b dark:bg-gray-800 dark:border-gray-700 miFila"

    const cabezera = document.createElement('th')
    cabezera.className = "px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
    cabezera.setAttribute("scope","row")

    const puerto = document.createElement('td')
    puerto.className = "px-6 py-4"
    puerto.textContent = estacion.puerto

    const hostName = document.createElement('td')
    hostName.className = "px-6 py-4"
    hostName.textContent = estacion.hostname

    const numCusi = document.createElement('td')
    numCusi.className = "px-6 py-4"
    numCusi.textContent = estacion.num_cusi

    const so = document.createElement('td')
    so.className = "px-6 py-4"
    so.textContent = estacion.so

    const depto = document.createElement('td')
    depto.className = "px-6 py-4"
    depto.textContent = estacion.nombre_depto

    const opciones = document.createElement('td')
    opciones.className = "flex justify-center items-center px-6 py-4 space-x-4 whitespace-nowrap mt-1"

    //const boton = document.createElement('button')
    //boton.setAttribute("data-modal-target","extralarge-modal")
    //boton.setAttribute("data-modal-toggle","extralarge-modal")
    //boton.setAttribute("type", "button");
    //boton.className = "btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
    //boton.textContent = "Ver Mas"


    const botonVerMas = document.createElement('a')
    botonVerMas.setAttribute("href", "#modal"); // Reemplaza la URL con la dirección que desees
    botonVerMas.setAttribute("id", "show-modal"); // Reemplaza "mi-boton" con el id que desees
    botonVerMas.setAttribute("onclick",'buscarEstacionPorPuerto(`'+estacion.puerto+'`)') //cambiar por el metodo de consulta
    botonVerMas.className = "btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
    botonVerMas.textContent = "Ver Mas"

    const botonVerUsuario = document.createElement('a')
    botonVerUsuario.setAttribute("href", "#modal_usuario"); // Reemplaza la URL con la dirección que desees
    botonVerUsuario.setAttribute("id", "show-modal"); // Reemplaza "mi-boton" con el id que desees
    botonVerUsuario.setAttribute("onclick",'buscarUsuarioPorId(`'+ estacion.id_usuario +'`,`' + estacion.puerto + '`)') //cambiar por el metodo de consulta
    botonVerUsuario.className = "btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
    botonVerUsuario.textContent = "Ver Usuario"

    const botonEliminarEstacion = document.createElement('a')
    botonEliminarEstacion.setAttribute("onclick",'eliminarEstacionCompleta(`' + estacion.puerto + '`)') //cambiar por el metodo de consulta
    botonEliminarEstacion.className = "red-options bg-red-500 hover:bg-blue-800 focus:ring-4"
    botonEliminarEstacion.textContent = "Eliminar"

    //+ ',' + estacion.puerto+

    body.append(row)
    row.append(puerto)
    row.append(numCusi)
    row.append(so)
    row.append(hostName)
    row.append(depto)
    row.append(opciones)
    opciones.append(botonVerMas)
    opciones.append(botonVerUsuario)
    opciones.append(botonEliminarEstacion)

}

async function cargarUsuarios(usuarios, puerto) {

   const body = document.querySelector(".tabla_usuario");

   const row = document.createElement('tr')
   row.className = "bg-white border-b dark:bg-gray-800 dark:border-gray-700"

   const puertoEstacion = document.createElement('td')
   puertoEstacion.className = "px-6 py-4"
   puertoEstacion.setAttribute("scope", "row");
   puertoEstacion.textContent = puerto;

   const usuario = document.createElement('td')
   usuario.className = "px-6 py-4"
   usuario.setAttribute("scope", "row");
   usuario.textContent = usuarios.usuario;


   const contraseña = document.createElement('td')
   contraseña.className = "px-6 py-4"
   contraseña.textContent = usuarios.clave;

  const opciones = document.createElement('td')
  opciones.className = "flex justify-center items-center px-1 py-1 space-x-2 whitespace-nowrap mt-1"

  const botonEliminar = document.createElement('button')
  botonEliminar.setAttribute("href", "#modal"); // Reemplaza la URL con la dirección que desees
  botonEliminar.setAttribute("onclick",'eliminarUsuario(`'+ usuarios.id_usuario +'`,`' + puerto + '`)')
  botonEliminar.className = "btn-options bg-red-500 hover:bg-red-800 focus:ring-4"
  botonEliminar.textContent = "Eliminar"

  row.append(puertoEstacion)
  row.append(usuario)
  row.append(contraseña)
  row.append(opciones)
  opciones.append(botonEliminar)
  body.append(row)

}

async function eliminarUsuario(id_usuario, puerto){

    console.log(puerto)

    const confirmacion = window.confirm('¿Quieres continuar con esta acción?');

    if (confirmacion) {

          const request = await fetch('/usuario/'+puerto+'/'+id_usuario, {
              method: 'DELETE',
              headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              }

          });
          const estaciones = await request.json();

    } else {
        console.log("no se elimino usuario (cancelado)")
    }



}


async function eliminarEstacionCompleta(puerto){

    const confirmacion = window.confirm('¿Seguro que quieres Borrar Estacion? Recuerda que el borrar esto borrara tanto Cusi como monitor asociado...');

    if (confirmacion) {

          const request = await fetch('/estacion/'+puerto, {
              method: 'DELETE',
              headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              }

          });
          const estaciones = await request.json();
          setTimeout(refresco, 1000);

    } else {
        console.log("no se elimino estacion (cancelado)")
    }



}

async function mostrarDatosInicio(){

        const request = await fetch('/main/getEstaciones', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        });
        const estaciones = await request.json();

        console.log(estaciones);


        for (let estacion of estaciones) {

               cargarEstaciones(estacion);

        }

}

async function mostrarDatosDepto(depto){

        const request = await fetch('/main/getEstacionesPorDepartamento/'+depto, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        });
        const estaciones = await request.json();

        console.log(estaciones);


        for (let estacion of estaciones) {

               cargarEstaciones(estacion);

        }

}

async function buscarEstacionPorPuerto(puerto){

        const request = await fetch('/main/getVerMasEstacionPorPuerto/'+puerto, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        });

        const estaciones = await request.json();

        console.log(estaciones);

        for (let estacion of estaciones) {

               cargarEstacionesEnModal(estacion);

        }

}

async function buscarUsuarioPorId(id_usuario, puerto){

        const request = await fetch('/main/getUsuario/'+id_usuario, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }

        });

        const usuarios = await request.json();

        const body = document.querySelector(".tabla_usuario");

        removerChildNodes(body)

        for (let usuario of usuarios) {

            cargarUsuarios(usuario,puerto);

        }

}

function cargarEstacionesEnModal(estacion){

    console.log(estacion)

    // ---------- CUSI -----------------

    const disco = document.getElementById('disco_id');
    disco.value = estacion.disco || "NO DATOS";
    const micro = document.getElementById('micro_id');
    micro.value = estacion.micro || "NO DATOS";
    const mother = document.getElementById('mother_id');
    mother.value = estacion.mother || "NO DATOS";
    const ram = document.getElementById('ram_id');
    ram.value = estacion.ram || "NO DATOS";
    const so = document.getElementById('so_id');
    so.value = estacion.so || "NO DATOS";

    const dvd = document.getElementById('dvd_id');
    dvd.checked = estacion.dvd
    const mouse = document.getElementById('mouse_id');
    mouse.checked = estacion.mouse
    const teclado = document.getElementById('teclado_id');
    teclado.checked = estacion.teclado

    // ----------- MONITOR --------------

    const marca = document.getElementById('marca_id');
    marca.value = estacion.marca || "NO DATOS";
    const modelo = document.getElementById('modelo_id');
    modelo.value = estacion.modelo || "NO DATOS";
    const numSerieId = document.getElementById('num_serie_id');
    numSerieId.value = estacion.numero_serie;

    // ----------- ESTACION --------------

    const puerto = document.getElementById('puerto_id');
    puerto.value = estacion.puerto;
    const hostName = document.getElementById('host_name_id');
    hostName.value = estacion.hostname || "NO DATOS";
    const mac = document.getElementById('mac_id');
    mac.value = estacion.mac || "NO DATOS";
    console.log(hostName)


}

async function mostrarDatosBuscadosPorCusiPuertoUsuario(){

        var selectElementBusqueda = document.getElementById('selectorBusqueda');
        console.log('busqueda por:', selectElementBusqueda.value);

        var input = document.getElementById("buscador");
        var value = input.value;

        console.log("--data--")
        console.log(selectElementBusqueda.value)
        console.log(value)

        /*

        const request = await fetch('/main', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ buscarPor: selectElementBusqueda.value, buscar: value })

        });
        */

        let headersList = {
         'Accept': 'application/json',
         "User-Agent": "Thunder Client (https://www.thunderclient.com)",
         "Content-Type": "application/json"
        }

        let request = await fetch("http://172.20.255.23:4040/main/buscarPorPuertoCusiUsuario", {
          method: "POST",
          body: JSON.stringify({ buscarPor: selectElementBusqueda.value, buscar: value }),
          headers: headersList
        });

        const estaciones = await request.json();


        console.log(estaciones);

        const row = document.querySelector(".tablita")

        removerChildNodes(row);

        console.log(estacion)

        for (let estacion of estaciones) {

               cargarEstaciones(estacion);

        }


}

var selectElementDeptos = document.getElementById('selectorDepto');

selectElementDeptos.addEventListener('change', function() {

  console.log('El valor seleccionado ha cambiado:', selectElementDeptos.value);

  const row = document.querySelector(".tablita")

  removerChildNodes(row);

  mostrarDatosDepto(selectElementDeptos.value);

});

function removerChildNodes(parent){

    while(parent.firstChild){
        parent.removeChild(parent.firstChild)
    }

}

function perromono(){

//      <button
//    const botonHTML = `
//        data-modal-target="extralarge-modal"
//        data-modal-toggle="extralarge-modal"
//        class="btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
//        type="button"
//      >
//        Ver Mas
//      </button>
//    `;

    //const boton = document.createElement('button')
    //boton.setAttribute("data-modal-target","extralarge-modal")
    //boton.setAttribute("data-modal-toggle","extralarge-modal")
    //boton.setAttribute("type", "button");
    //boton.className = "btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
    //boton.textContent = "Ver Mas"

    //<a href="#modal" id="show-modal" class="botons uno"><span>Leer Mas</span></a>

        const boton = document.createElement('a')
        boton.setAttribute("href", "#modal"); // Reemplaza la URL con la dirección que desees
        boton.setAttribute("id", "show-modal"); // Reemplaza "mi-boton" con el id que desees
        boton.className = "btn-options bg-blue-500 hover:bg-blue-800 focus:ring-4"
        boton.textContent = "Ver Mas"




    const perromono = document.querySelector(".perromono");
    perromono.append(boton)


}

function pepe(){

    alert("hola perro")

}

function refresco() {
    location.reload();
}


mostrarDatosInicio();


