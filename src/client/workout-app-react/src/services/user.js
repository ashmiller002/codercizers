const url = "http://localhost:8080";


export async function getUserWithLoginId(id, jwt_token) {
    const init = {
        headers: {
             "Authorization": `Bearer ${jwt_token}`
        }
    };
    const response = await fetch(`${url}/api/user/${id}`, init);
    if (response.status === 200) {
        return await response.json();
    }
    if (response.status === 404) {
        return Promise.reject(["No user found with those credentials."])
    }
    return Promise.reject(["login failed"]);
}

export async function registerUser(user) {

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
        },
        body: JSON.stringify(user)
    };
    return fetch(`${url}/api/user/register`, init)
        .then(async response => {
            if (response.status === 201) {
                return response.json();
            }
            const messages = await response.json();
            return Promise.reject(messages);
        });
}

export async function editUser(user) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(user)
    };
    return fetch(`${url}/workouts/${user.loginId}`, init)
    .then(async response => {
        if (response.status === 204) {
            return response.json();
        }
        if (response.status === 404) {
            return Promise.reject(["User was not found."]);
        }
        if (response.status === 403) {
            return Promise.reject(["Something went wrong on our end"]);
        }
        const messages = await response.json();
        return Promise.reject(messages);
    });
}