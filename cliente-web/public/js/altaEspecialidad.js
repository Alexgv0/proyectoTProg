const formAltaEspecialidad = document.getElementById("formAltaEspecialidad");
const nombre = document.getElementById("nombre");

formAltaEspecialidad.addEventListener("submit", function(event) {
	if (nombre.value.trim() === "") {
		event.preventDefault();
		alert("El nombre no puede contener solo espacios.");
	}
});
