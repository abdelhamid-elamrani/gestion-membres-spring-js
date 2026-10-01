const url = "http://localhost:8080/api";


// Auth
async function login(email, password) {
    try {

        let response = await fetch(url + "/auth/loginsubmit", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });


        if (response.status === 401) {
            throw new Error('Email ou mot de passe incorrect');
        }

        if (!response.ok) {
            throw new Error('Erreur de connexion');
        }

        let data = await response.json();

        return data;

    } catch (err) {
        throw err;
    }

}



async function register(firstName, lastName, email, phoneNumbr, password, confirmPassword) {
    try {
        let response = await fetch(url + `/auth/activatesubmit`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                email: email,
                phoneNumbr: phoneNumbr,
                password: password,
                confirmPassword: confirmPassword
            })
        });


        //badRequest === status(400)
        if (response.status === 400) {
            let messageErreur = await response.text();
            throw new Error(messageErreur);
        }


        if (!response.ok) {
            throw new Error('Erreur de connexion');

        }

        return await response.text();


    } catch (err) {
        throw err;

    }
}


//Client

async function getProfile(id) {
    try {
        const response = await fetch(url + `/client/profile/${id}`);

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");

        }

        return await response.json();


    } catch (err) {
        throw err;
    }

}

async function editProfile(id, firstName, phoneNumbr, lastName) {
    try {
        let response = await fetch(`${url}/client/profile/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                phoneNumbr: phoneNumbr
            })
        });

        if (response.status === 400) {
            let msgErreur = await response.text();
            throw new Error(msgErreur);
        }

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");
        }

        return await response.text();
    } catch (err) {
        throw err;
    }

}

async function editPassword(id, oldPassword, password, confirmPassword) {
    try {
        let response = await fetch(`${url}/client/password/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                oldPassword: oldPassword,
                password: password,
                confirmPassword: confirmPassword
            })
        });

        if (response.status === 400) {
            let msgErreur = await response.text();
            throw new Error(msgErreur);
        }

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");
        }

        return await response.text();
    } catch (err) {
        throw err;
    }

}


//Admin

async function getMembers() {

    try {

        const response = await fetch(`${url}/admin/members`);

        if (!response.ok) {
            throw new Error("Erreur !");
        }

        const data = await response.json();

        return data;
    } catch (err) {
        throw err;
    }

}



async function editMember(id, phoneNumbr, firstName, lastName, role) {
    try {
        let response = await fetch(`${url}/admin/member/${id}/edit`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                phoneNumbr: phoneNumbr,
                firstName: firstName,
                lastName: lastName,
                role: role
            })
        });

        if (response.status === 400) {
            let msgErreur = await response.text();
            throw new Error(msgErreur);
        }

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");
        }

        return await response.text();
    } catch (err) {
        throw err;
    }

}



async function deleteMember(id) {
    try {
        let response = await fetch(`${url}/admin/member/${id}/delete`, {
            method: "DELETE"
        });

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");
        }

        return await response.text();
    } catch (err) {
        throw err;
    }

}


async function createMember(firstName, lastName, email, phoneNumbr, role) {
    try {
        let response = await fetch(`${url}/admin/member/create`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                email: email,
                phoneNumbr: phoneNumbr,
                role: role
            })
        });

        if (response.status === 400) {
            let msgErreur = await response.text();
            throw new Error(msgErreur);
        }

        if (!response.ok) {
            throw new Error("pas de membre de cet id !");
        }

        return await response.text();
    } catch (err) {
        throw err;
    }
}


