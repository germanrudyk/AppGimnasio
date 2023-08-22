// --------------Ingresar cliente por nombre y apellido-----------------

let input = document.getElementById("input");

input.addEventListener('input', function() {
    var filtro = this.value.toLowerCase(); // Obtener el valor del input en minúsculas
    var filas = document.getElementsByClassName('fila');

    // Recorrer cada fila de la tabla
    for (var i = 0; i < filas.length; i++) {
      var contenidoFila = filas[i].textContent.toLowerCase(); // Obtener el contenido de la fila en minúsculas
      
      // Comparar el contenido de la fila con el término de búsqueda
      if (contenidoFila.indexOf(filtro) === -1) {
        filas[i].style.display = 'none'; // Ocultar la fila si no coincide
      } else {
        filas[i].style.display = ''; // Mostrar la fila si coincide
      }
    }
  });

  // ----------------Filtrar por encabezado---------------------

  var botonesOrden = document.getElementsByClassName('boton-orden');
  var vencimiento = document.getElementsByClassName("vencimiento");

  // Agregar el evento click a cada botón de orden

  vencimiento[0].addEventListener("click", function(){
    let filas = Array.from(document.getElementsByClassName('fila'));
    let tipoOrden = this.getAttribute('data-sort');

    // Ordenar las filas según la columna seleccionada y el tipo de orden
    filas.sort(function(a, b) {
      let contenidoA = a.cells[1].value;
      let contenidoB = b.cells[1].value;

      console.log(contenidoA)
      console.log(contenidoB)

      // Convertir las fechas a objetos Date para comparar
      let fechaA = new Date(contenidoA);
      let fechaB = new Date(contenidoB);

      if (tipoOrden === 'asc') {
        return fechaA - fechaB;
      } else {
        return fechaB - fechaA;
      }
    });

    // Agregar y remover la clase CSS en las filas ordenadas
    var tabla = document.getElementsByTagName('table')[1];
    for (let j = 0; j < filas.length; j++) {
      tabla.appendChild(filas[j]);
      filas[j].classList.add("table");

    // Cambiar el tipo de orden del botón
    if (tipoOrden === 'asc') {
      this.setAttribute('data-sort', 'desc');
    } else {
      this.setAttribute('data-sort', 'asc');
    }
  }
});

  for (let i = 0; i < botonesOrden.length; i++) {
    botonesOrden[i].addEventListener('click', function() {
      let columna = this.parentNode.cellIndex; // Obtener el índice de la columna del encabezado
      let tipoOrden = this.getAttribute('data-sort'); // Obtener el tipo de orden del botón

      // Obtener todas las filas de la tabla
      var filas = Array.from(document.getElementsByClassName('fila'));

      // Ordenar las filas según la columna seleccionada y el tipo de orden
      filas.sort(function(a, b) {
        let contenidoA = a.cells[columna].textContent.toLowerCase();
        let contenidoB = b.cells[columna].textContent.toLowerCase();

        if (tipoOrden === 'asc') {
          return contenidoA.localeCompare(contenidoB);
        } else {
          return contenidoB.localeCompare(contenidoA);
        }
      });

      // Actualizar la tabla con las filas ordenadas
      let tabla = document.getElementsByClassName('table')[1];
      for (var j = 0; j < filas.length; j++) {
        tabla.appendChild(filas[j]);
        filas[j].classList.add("table");
      }

      // Cambiar el tipo de orden del botón
      if (tipoOrden === 'asc') {
        this.setAttribute('data-sort', 'desc');
      } else {
        this.setAttribute('data-sort', 'asc');
      }
    });
  }

  //-------------Agregar items---------------//

  let items = document.getElementById("items");

  let agregarItem = document.getElementById("agregarItem");

  agregarItem.addEventListener("click", function(){

    let elemento = document.createElement("input");

    elemento.classList.add("item");

    items.appendChild(elemento);
    console.log("echo");

  })