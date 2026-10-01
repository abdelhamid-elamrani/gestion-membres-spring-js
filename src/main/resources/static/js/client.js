const prenom = document.querySelector("#namee");
const btnLogout = document.querySelector("#btnLogout");
const msg = document.querySelector("#msg");
const editProf = document.querySelector("#editProf");
const editPass = document.querySelector("#editPass");
const tbody = document.querySelector("#tbody");
const id = localStorage.getItem("userId");


if(id !== null){
    prenom.textContent = localStorage.getItem("firstName");

    btnLogout.addEventListener("click", function() {
        localStorage.clear();
        window.location.href = "login.html";
    });

    editProf.addEventListener("click", function() {
        window.location.href = "clientProfile.html";
    });

    editPass.addEventListener("click", function() {
        window.location.href = "clientPassword.html";
    }); 

    

}else{
    msg.textContent = "Vous n'êtes pas connecté. Veuillez vous connecter pour accéder à votre profil.";
    msg.style.display = "block";
    msg.style.color = "red";
    setTimeout(()=>{
        window.location.href = "login.html";
    }, 2000);
    
}

async function loadProfile() {
    try{
        const mbr = await getProfile(id);
        const tr = document.createElement("tr")
        tr.innerHTML = `
            <td>
                ${mbr.firstName}
            </td>
            <td>
                ${mbr.lastName}
            </td>
            <td>
                ${mbr.email}
            </td>
            <td>
                ${mbr.phoneNumbr}
            </td>
        `;
        tbody.appendChild(tr);
    }catch(err){
        msg.textContent = err.message;
        msg.style.display = "block";
        msg.style.color = "red";
    }
        
};

loadProfile();