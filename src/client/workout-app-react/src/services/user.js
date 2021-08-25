const url = "http://localhost:8080";
const token = localStorage.getItem('jwt_token');

export async function getUserWithLoginId(id) {
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    }

}

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