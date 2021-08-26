const url = "http://localhost:5000";

export async function authenticate(credentials) {
  const init = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Accept": "application/json"
    },
    body: JSON.stringify(credentials)
  };

  return fetch(`${url}/authenticate`, init)
    .then(response => {
      if (response.status === 200) {
        return response.json();
      } else if (response.status === 403) {
        return null;
      }
      return Promise.reject("Something went wrong :(");
    });
}

export async function register(credentials) {
    const init = {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify(credentials)
    };
  
    return fetch(`${url}/create_account`, init)
      .then(async response => {
        if (response.status === 201) {
          return response.json();
        }
        const messages = await response.json();
        return Promise.reject(messages);
      });
  }