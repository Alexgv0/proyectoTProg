const radioDiaConsultas = document.querySelectorAll("#selectorDiaConsulta input");
const radioConsultas = document.querySelectorAll("#selectorConsulta input");
const datosDiaConsulta = document.getElementById("datosDiaConsulta");
const listaConsultas = document.getElementById("listaConsultas");
const infoConsultas = document.getElementById("infoConsulta");
const listaReservas = document.getElementById("listaReserva");
const infoReserva = document.getElementById("infoReserva");

const cargarConsultas = () => {
	radioDiaConsultas.forEach(input => {
		input.addEventListener("change", () => {
			if (datosDiaConsulta) {
				datosDiaConsulta.hidden = false;
			    datosDiaConsulta.scrollIntoView({
			        behavior: "smooth",
			        block: "start"
			    });
			}
		
			if (listaConsultas) {
				listaConsultas.hidden = false;
				listaConsultas.scrollIntoView({
					behavior: "smooth",
					block: "start"
				});
			}
		});
	});	
}

const cargarDatosConsulta = () => {
	radioConsultas.forEach(input => {
		input.addEventListener("change", () => {
			if (infoConsultas) {
				infoConsultas.hidden = false;
			    infoConsultas.scrollIntoView({
			        behavior: "smooth",
			        block: "start"
			    });
				if (listaReservas) {
					listaReservas.hidden = false;
				}
				if (infoReserva) {
					infoReserva.hidden = false;
				}
			}
		});
	});
}

cargarConsultas();
cargarDatosConsulta();