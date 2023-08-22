 //-------------Agregar items---------------//

  let items = document.getElementById("items");

  let agregarItem = document.getElementById("agregarItem");

  agregarItem.addEventListener("click", function(){

    let elemento = document.createElement("input");

    elemento.classList.add("form-control");
    elemento.classList.add("item");
    elemento.classList.add("w-75");
    elemento.classList.add("mb-3");
    elemento.setAttribute("placeholder", "Escriba dia o ejercicio");

    items.appendChild(elemento);

  });

  //--------------Unir inputs para form---------------//

  const rutina = document.getElementById("rutina");

  rutina.addEventListener("submit", function(e){

    e.preventDefault();

    console.log(document.getElementsByClassName("item")[0].value);
    console.log(document.getElementsByClassName("item")[1].value);
    console.log(document.getElementsByClassName("item").length);

    let detalle = "";

    for(let i = 0; i < document.getElementsByClassName("item").length; i++){

        console.log(document.getElementsByClassName("item").length);

        detalle += document.getElementsByClassName("item")[i].value + "\n";

    }

    let elemento = document.createElement("input");

    elemento.setAttribute("type", "hidden")
    elemento.setAttribute("name", "detalle")
    elemento.setAttribute("value", detalle)

    this.appendChild(elemento);

    rutina.submit();

  })