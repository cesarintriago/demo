// Call the dataTables jQuery plugin
$(document).ready(function() {      //Cuando el documento esté listo
    //On ready
});


async function registarUsuario(){
    let datos = { };
    datos.nombre = document.getElementById("txtNombre").value;
    datos.apellido = document.getElementById("txtApellido").value;
    datos.email = document.getElementById("txtEmail").value;
    datos.password = document.getElementById("txtPassword").value;

    let repetirPassword = document.getElementById("txtRepetirPassword").value;

    if(datos.password != repetirPassword){  
        alert("Las contraseñas no coinciden");
        return;
    }

    const request = await fetch('api/usuarios', {   //Hacemos una petición al servidor
        method: 'POST',           //Método de envío
        headers: {              //Estos headers son necesarios para que el servidor sepa que tipo de datos se le envían
            'Accept': 'application/json',   //Le decimos que aceptamos JSON
            'Content-Type': 'application/json'  //Le decimos que el contenido es JSON

        },
        body: JSON.stringify(datos)//Esta función toma un objeto de JavaScript y lo convierte en un string
    });

    alert("Usuario registrado con éxito");
     window.location.href = "login.html";
}
