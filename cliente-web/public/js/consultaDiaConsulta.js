const radioConsultas = document.querySelectorAll("#selectorDiaConsulta input");
const listaConsultas = document.getElementById("listaConsultas");

radioConsultas.forEach(input => {
	input.addEventListener("change", () => {
		listaConsultas.hidden = false;
	
	    listaConsultas.scrollIntoView({
	        behavior: "smooth",
	        block: "start"
	    });

	});
});