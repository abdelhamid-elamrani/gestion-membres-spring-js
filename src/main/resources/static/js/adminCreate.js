const createMemberForm = document.getElementById("createMemberForm");

const message = document.getElementById("message");

const firstName = document.getElementById("firstName");

const lastName = document.getElementById("lastName");

const email = document.getElementById("email");

const phone = document.getElementById("phone");

const role = document.getElementById("role");


if (localStorage.getItem("userId") && localStorage.getItem("role") === "ADMIN") {
    document.getElementById('namee').textContent = localStorage.getItem('firstName');

    createMemberForm.addEventListener("submit", async function (e) {
        try {
            e.preventDefault();
            let valFirstName = firstName.value;
            let valLastName = lastName.value;
            let valPhone = phone.value;
            let valRole = role.value;
            let ValEmail = email.value;

            await createMember(valFirstName, valLastName, ValEmail, valPhone, valRole);

            alert("Membre crée avec succès !");
            setTimeout(() => {
                window.location.href = "adminDashboard.html";
            }, 2000);

        } catch (err) {
            message.textContent = err.message;
            message.style.display = "block";
        }
    });
    document.getElementById('btnCancel').addEventListener('click', function () {
        window.location.href = 'adminDashboard.html';
    });
} else {
    window.location.href = "login.html"
}

