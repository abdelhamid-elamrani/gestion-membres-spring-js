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

// Charger et pré-remplir le formulaire
async function loadAndFillForm() {
    try {
        const mbr = await getProfile(id);
        
        document.getElementById("firstName").value = mbr.firstName;
        document.getElementById("lastName").value = mbr.lastName;
        document.getElementById("phoneNumbr").value = mbr.phoneNumbr;
        document.getElementById("email").value = mbr.email;
        
    } catch(err) {
        msg.textContent = err.message;
        msg.style.display = "block";
        msg.style.color = "red";
    }
}

// Charger le profil au démarrage
loadAndFillForm();

// Gérer la modification du profil
formProfile.addEventListener("submit", async function(e) {
    e.preventDefault();
    
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const phoneNumbr = document.getElementById("phoneNumbr").value;
    
    try {
        await editProfile(id, firstName, phoneNumbr, lastName);
        
        msg.textContent = "Profil modifié avec succès !";
        msg.style.color = "green";
        msg.style.display = "block";
        
        // Mettre à jour le localStorage
        localStorage.setItem("firstName", firstName);
        localStorage.setItem("lastName", lastName);
        localStorage.setItem("phone", phoneNumbr);
        
        setTimeout(() => {
            window.location.href = "clientDashboard.html";
        }, 2000);
        
    } catch(err) {
        msg.textContent = err.message;
        msg.style.color = "red";
        msg.style.display = "block";
    }
});
