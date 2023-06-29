async function cargarEstaciones(postulante) {

    const body = document.querySelector(".tablita");
    
    const row = document.createElement('tr')
    row.className = "bg-white border-b dark:bg-gray-800 dark:border-gray-700 miFila"

    const cabezera = document.createElement('th')
    cabezera.className = "px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
    cabezera.setAttribute("scope","row")

    const puerto = document.createElement('td')
    puerto.className = "px-6 py-4"
    puerto.textContent = postulante.puerto

    const hostName = document.createElement('td')
    hostName.className = "px-6 py-4"
    hostName.textContent = postulante.hostname

    const numCusi = document.createElement('td')
    numCusi.className = "px-6 py-4"
    numCusi.textContent = postulante.num_cusi

    const so = document.createElement('td')
    so.className = "px-6 py-4"
    so.textContent = postulante.so

    const depto = document.createElement('td')
    depto.className = "px-6 py-4"
    depto.textContent = postulante.nombre_depto

    const opciones = document.createElement('td')
    opciones.className = "flex justify-center items-center px-6 py-4 space-x-4 whitespace-nowrap mt-1"

    const boton = document.createElement('button')
    boton.className = "block text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
    boton.textContent = "Ver Mas"
    boton.setAttribute("data-modal-target","modalMasInfoStatic")
    boton.setAttribute("data-modal-toggle","modalMasInfoStatic")

    body.append(row)
    row.append(puerto)
    row.append(numCusi)
    row.append(so)
    row.append(hostName)
    row.append(depto)
    row.append(opciones)
    opciones.append(boton)

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

var selectElementDeptos = document.getElementById('selector');

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

mostrarDatosInicio();


