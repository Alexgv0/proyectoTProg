const fechaActual = new Date();
const hoy = new Date().toISOString().split("T")[0];
const fechaMinima = new Date(fechaActual);
fechaMinima.setFullYear(fechaActual.getFullYear() - 150);
const hace150Anios = fechaMinima.toISOString().split("T")[0];
const nickname = document.getElementById("nickname");
const nombre = document.getElementById("nombre");
const apellido = document.getElementById("apellido");
const tipoUsuario = document.getElementById("tipoUsuario");
const datosPaciente = document.getElementById("datosPaciente");
const datosMedico = document.getElementById("datosMedico");
const contrasenia = document.getElementById("contrasenia");
const confirmarContrasenia = document.getElementById("confirmarContrasenia");
const especialidades = document.querySelectorAll('input[name="especialidades"]');
const formAltaUsuario = document.getElementById("formAltaUsuario");
const fechaNacimiento = document.getElementById("fechaNacimiento");
fechaNacimiento.min = hace150Anios;
fechaNacimiento.max = hoy;
datosPaciente.style.display = "none";
datosMedico.style.display = "none";
tipoUsuario.addEventListener("change", function() {
	if (tipoUsuario.value === "paciente") {
	    datosPaciente.style.display = "block";
	    datosMedico.style.display = "none";
	    document.getElementById("fechaNacimiento").required = true;
	    document.getElementById("documento").required = true;
	    document.getElementById("numeroRegistro").required = false;
	    document.getElementById("mesLicencia").required = false;
	    document.getElementById("porcentajeAdicional").required = false;
	}
	else if (tipoUsuario.value === "medico") {
	    datosPaciente.style.display = "none";
	    datosMedico.style.display = "block";
	    document.getElementById("fechaNacimiento").required = false;
	    document.getElementById("documento").required = false;
	    document.getElementById("numeroRegistro").required = true;
	    document.getElementById("mesLicencia").required = true;
	    document.getElementById("porcentajeAdicional").required = true;
	}
    else {
        datosPaciente.style.display = "none";
        datosMedico.style.display = "none";
    }
});
confirmarContrasenia.addEventListener("input", function() {
    if (contrasenia.value !== confirmarContrasenia.value) {
        confirmarContrasenia.setCustomValidity(
            "Las contraseñas no coinciden"
        );
    }
    else {
        confirmarContrasenia.setCustomValidity("");
    }
});
formAltaUsuario.addEventListener("submit", function(event) {
    if (tipoUsuario.value === "medico") {
        let especialidadSeleccionada = false;
        especialidades.forEach(function(especialidad) {
            if (especialidad.checked) {
                especialidadSeleccionada = true;
            }
        });
        if (!especialidadSeleccionada) {
            event.preventDefault();
            alert("Debe seleccionar al menos una especialidad.");
        }
    }
});