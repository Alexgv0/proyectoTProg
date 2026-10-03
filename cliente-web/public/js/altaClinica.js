const formAltaClinica = document.getElementById("formAltaClinica");
const nombre = document.getElementById("nombre");
const descripcion = document.getElementById("descripcion");
const direccion = document.getElementById("direccion");

formAltaClinica.addEventListener("submit", function(event) {
	if (nombre.value.trim() === "" ||
		descripcion.value.trim() === "" ||
		direccion.value.trim() === "") {
		event.preventDefault();
		alert("Nombre, descripción y dirección no pueden contener solo espacios.");
	}
});
