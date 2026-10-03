const fechaActual = new Date();
const hoy = new Date().toISOString().split("T")[0];
const fechaMinima = new Date(fechaActual);
fechaMinima.setFullYear(fechaActual.getFullYear() - 150);
const hace150Anios = fechaMinima.toISOString().split("T")[0];
const fechaNacimiento = document.getElementById("fechaNacimiento");
fechaNacimiento.min = hace150Anios;
fechaNacimiento.max = hoy;
const fechaIngreso = document.getElementById("fechaIngreso");
fechaIngreso.max = hoy;
const nuevaContrasenia = document.getElementById("nuevaContrasenia");
const confirmarContrasenia = document.getElementById("confirmarContrasenia");
function validarContrasenias() {
    if (nuevaContrasenia.value !== confirmarContrasenia.value) {
        confirmarContrasenia.setCustomValidity(
            "Las contraseñas no coinciden"
        );
    }
    else {
        confirmarContrasenia.setCustomValidity("");
    }
}
nuevaContrasenia.addEventListener("input", validarContrasenias);
confirmarContrasenia.addEventListener("input", validarContrasenias);
const formModificarUsuario = document.getElementById("formModificarUsuario");
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
formModificarUsuario.addEventListener("submit", function(event) {
    if (nombre.value.trim() === "" ||
        apellido.value.trim() === "") {
        event.preventDefault();
        alert("Nombre y apellido no pueden contener solo espacios.");
        return;
    }
});