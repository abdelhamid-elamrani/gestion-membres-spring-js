const email = document.getElementById("email");
const password = document.getElementById("password");
const submitLogin = document.getElementById("loginForm");
const div = document.getElementById("error");


submitLogin.addEventListener("submit", async function (e) {
    e.preventDefault();
    const valEmail = email.value;
    const valPassword = password.value;

    try {
        let resultat = await login(valEmail, valPassword);

        localStorage.setItem("userId", resultat.id)
        localStorage.setItem("email", resultat.email);
        localStorage.setItem("role", resultat.role);
        localStorage.setItem("firstName", resultat.firstName);
        localStorage.setItem("lastName", resultat.lastName);
        localStorage.setItem("phone", resultat.phoneNumbr);

        if (resultat.role === 'ADMIN') {
            window.location.href = 'adminDashboard.html';
        } else {
            window.location.href = 'clientDashboard.html';
        }

    } catch (err) {

        div.innerText = err.message;
        div.style.display = "block";
    }
})


const registerForm = document.getElementById("registerForm");
const prenom = document.getElementById("firstName");
const nom = document.getElementById("lastName");
const emailReg = document.getElementById("emailReg");
const tele = document.getElementById("tele");
const passwordReg = document.getElementById("passwordReg");
const confirmPassword = document.getElementById("confirmPassword");

registerForm.addEventListener("submit", async function (e) {
    e.preventDefault();
    const prenomVal = prenom.value;
    const nomVal = nom.value;
    const emailVal = emailReg.value;
    const telVal = tele.value;
    const passwordVal = passwordReg.value;
    const confirmVal = confirmPassword.value;

    try {
        if (passwordVal !== confirmVal) {

            div.textContent = "Les mots de passe ne correspondent pas !";
            div.style.color = "red";
            div.style.display = "block";
            return;  // Arrêter ici

        }

        await register(prenomVal, nomVal, emailVal, telVal, passwordVal, confirmVal);

        alert("Eregistré !");
        setTimeout(() => {
            window.location.href = "login.html";
        }, 1000);



    } catch (err) {
        div.innerText = err.message;
        div.style.display = "block";
    }
});
