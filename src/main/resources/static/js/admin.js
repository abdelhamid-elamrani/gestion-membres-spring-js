const btnLogout = document.getElementById('btnLogout');
const namee = document.getElementById('namee');
const message = document.getElementById('message');
const membersTableBody = document.getElementById('membersTableBody');

const userId = localStorage.getItem("userId");
if (!userId) {
    window.location.href = "login.html";
}

async function loadMembers(){
    try{
        let data = await getMembers();
        data.forEach((e) => {
            const tr = document.createElement("tr");
            tr.innerHTML = `
                <td>
                    ${e.id}
                </td>
                <td>
                    ${e.firstName}
                </td>
                <td>
                    ${e.lastName}
                </td>
                <td>
                    ${e.email}
                </td>
                <td>
                    ${e.phoneNumbr}
                </td>
                <td>
                    ${e.role}
                </td>
                <td>
                    <button class="btnSupprimer">Supprimer</button>
                </td>`
            const btnSupprimer = tr.querySelector(".btnSupprimer");
            btnSupprimer.addEventListener("click", async function (){
                try{
                    const confirmation = confirm('Êtes-vous sûr de vouloir supprimer ce membre ?');
                    if (confirmation) {
                        await deleteMember(e.id);
                        tr.remove();  // Retirer la ligne du tableau
                        message.textContent = "Membre supprimé avec succès !";  
                    }
                }catch(err){
                    message.textContent = err.message;
                }finally{
                    message.style.display = "block";
                }
            }); 
            membersTableBody.appendChild(tr);
        })
    }catch(err){
        message.textContent = err.message;
        message.style.display = "block";
    }
}


const userName = localStorage.getItem("firstName");
const role = localStorage.getItem("role");

if (role === "ADMIN"){
    namee.textContent = localStorage.getItem('firstName');
    loadMembers();
}else{
    window.location.href = "login.html";
}

btnLogout.addEventListener("click", () => {
    localStorage.clear();
    window.location.href = "login.html";
});


const btnCreate = document.getElementById("btnCreate"); 

btnCreate.addEventListener("click", function (){
        window.location.href = "adminCreate.html";
});