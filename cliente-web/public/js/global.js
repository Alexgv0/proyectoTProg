
const validarDatos = () => {
	document.addEventListener("DOMContentLoaded", () => {
		limpiarCampos();
		validarEmails();
		validarPasswords();
	});
}

const limpiarCampos = () => {

    document.querySelectorAll("input[type='text'], input[type='email'], textarea").forEach(campo => {

        campo.addEventListener("blur", () => {
            campo.value = campo.value.trim();
        });

    });

};

const validarEmails = () => {
	document.querySelectorAll(".email").forEach(campo => {
		campo.addEventListener("blur", () => {
			
			const expresionRegular = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		
		    if (!expresionRegular.test(campo.value)) {
		        campo.classList.add("campo-error");
		        return;
		    }
		
		    campo.classList.remove("campo-error");
			
		});
	});
}

const validarPasswords = () => {

    document.querySelectorAll(".password").forEach(campo => {

        const min = 8;
        const max = 64;

        campo.addEventListener("blur", () => {
            if (campo.value.length < min) {
                campo.classList.add("campo-error");
                alert("La contraseña debe tener al menos 8 caracteres de largo.");
                return;
            }
            campo.classList.remove("campo-error");
        });

        campo.addEventListener("input", () => {
            if (campo.value.length >= max - 10) {
                console.log(
                    `Te quedan ${max - campo.value.length} caracteres.`
                );
            }
        });

    });
};

validarDatos();