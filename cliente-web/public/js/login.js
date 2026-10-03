const formLogin = document.getElementById("formLogin");
const nickname = document.getElementById("nickname");
formLogin.addEventListener("submit", function(event) {
    if (nickname.value.trim() === "") {
        event.preventDefault();
        alert("El nickname no puede contener solo espacios.");
    }
});