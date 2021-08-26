const url = "http://localhost:8080";
const token = localStorage.getItem('jwt_token');

export async function getUserWithLoginId(id) {
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/workouts/getuser/${id}`, init);
    if (response.status === 200) {
        return await response.json();
    }
    if (response.status === 404) {
        return Promise.reject(["No user found with those credentials."])
    }
    const messages = await response.json();
    return Promise.reject([messages]);
    // why is it skipping over this .then and .catch?
}

// export async function getUserWithLoginId(id) {
//     const init = {
//         headers: {
//             "Authorization": `Bearer ${token}`
//         },
//         body: {"id":`"${id}"`}
//     };
//     debugger;
//     return (fetch(`${url}/workouts/getuser`, init)
//     // why is it skipping over this .then and .catch?
//     .then(async response => {
//         if (response.status === 404) {
//             return Promise.reject(["No user found with those credentials."])
//         }
//         if (response.status === 200) {
//             return response.json();
//         }
//         const messages = await response.json();
//         return Promise.reject([messages]);
//     })
//     .catch(console.log)
//     )
// }

export async function registerUser(user) {
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json"
        },
        body: JSON.stringify(user)
    };
    return fetch(`${url}/workouts/create_account`, init)
        .then(async response => {
            if (response.status === 201) {
                return response.json();
            }
            const messages = await response.json();
            return Promise.reject(messages);
        });
}