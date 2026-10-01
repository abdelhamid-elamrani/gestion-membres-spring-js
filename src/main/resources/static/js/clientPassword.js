const namee = document.querySelector("#namee");
const btnLogout = document.querySelector("#btnLogout");
const msg = document.querySelector("#msg");
const formProfile = document.querySelector("#formProfile");
const formPassword = document.querySelector("#formPassword");
const btnCancel = document.querySelector("#btnCancel");

const id = localStorage.getItem("userId");

// Vérifier connexion
if (!id) {
    window.location.href = "login.html";
}

// Afficher le prénom
namee.textContent = localStorage.getItem("firstName");

// Bouton déconnexion
btnLogout.addEventListener("click", function() {
    localStorage.clear();
    window.location.href = "login.html";
});

// Bouton annuler
btnCancel.addEventListener("click", function() {
    window.location.href = "clientDashboard.html";
});

// Gérer le changement de mot de passe
formPassword.addEventListener("submit", async function(e) {
    e.preventDefault();
    
    const oldPassword = document.getElementById("oldPassword").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    
    try {
        // Vérifier que nouveau === confirmation
        if (newPassword !== confirmPassword) {
            msg.textContent = "Les mots de passe ne correspondent pas !";
            msg.style.color = "red";
            msg.style.display = "block";
            return;
        }
        
        await editPassword(id, oldPassword, newPassword, confirmPassword);
        
        msg.textContent = "Mot de passe modifié avec succès !";
        msg.style.color = "green";
        msg.style.display = "block";
        
        // Vider les champs
        document.getElementById("oldPassword").value = "";
        document.getElementById("newPassword").value = "";
        document.getElementById("confirmPassword").value = "";
        
    } catch(err) {
        msg.textContent = err.message;
        msg.style.color = "red";
        msg.style.display = "block";
    }
});