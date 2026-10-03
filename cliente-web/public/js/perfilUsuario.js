let imagen = "../img/perfilEjemplo.png";
let nickname = "juanperez";
let nombre = "Juan";
let apellido = "Pérez"
let correo = "juan@email.com"
let fechaIngreso = "01/10/2026"
let fechaNacimiento = "15/05/2000"
let documento = "12345678"
let antecedentes = "Ninguno"
let reserva1 = "Consulta de Cardiología - 15/10/2026";
let reserva2 = "Consulta de Medicina General - 22/10/2026";
let enlaceReserva = "../reservas/consultaReserva.html"; // Cambiar cuando esté creada la página de consulta de reserva
let cargarPerfil = () => {
	document.getElementById("imagenPerfil").src = imagen;
	document.getElementById("nickname").innerText = nickname;
	document.getElementById("nombre").innerText = nombre;
	document.getElementById("apellido").innerText = apellido;
	document.getElementById("correo").innerText = correo;
	document.getElementById("fechaIngreso").innerText = fechaIngreso;
	document.getElementById("fechaNacimiento").innerText = fechaNacimiento;
	document.getElementById("documento").innerText = documento;
	document.getElementById("antecedentes").innerText = antecedentes;
	document.getElementById("reserva1").innerText = reserva1;
	document.getElementById("reserva1").href = enlaceReserva;
	document.getElementById("reserva2").innerText = reserva2;
	document.getElementById("reserva2").href = enlaceReserva;
}
cargarPerfil();