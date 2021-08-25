const url = "http://localhost:8080";
const token = localStorage.getItem('jwt_token');

export async function getUserWithLoginId(id) {
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    }
    fetch
}