// Call the dataTables jQuery plugin
$(document).ready(function() {      //Cuando el documento esté listo
    //On ready
});


async function iniciarSesion(){
    let datos = { };
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;


    const request = await fetch('api/login', {   //Hacemos una petición al servidor
        method: 'POST',           //Método de envío
        headers: {              //Estos headers son necesarios para que el servidor sepa que tipo de datos se le envían
            'Accept': 'application/json',   //Le decimos que aceptamos JSON
            'Content-Type': 'application/json'  //Le decimos que el contenido es JSON

        },
        body: JSON.stringify(datos)//Esta función toma un objeto de JavaScript y lo convierte en un string
    });

    const respuesta = await request.text();//Esperamos la respuesta del servidor y la convertimos en un objeto de JavaScript

if(respuesta != 'FAIL'){
    localStorage.token = respuesta;
    localStorage.email = datos.email;
    window.location.href = "usuarios.html";
}else{
    alert("Usuario o contraseña incorrectos");
    }


}
