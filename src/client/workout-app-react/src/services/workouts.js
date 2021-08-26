const url="http://localhost:8080";
const token = localStorage.getItem('jwt_token');

export async function getWorkoutsByCategoryId(categoryId) {
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };

    fetch(`${url}/workouts/${categoryId}`, init)
        .then(response => {
            if (response.status !== 200) {
                return Promise.reject(["Suggested workout fetch failed"]);
            }
            return response.json();
        })
        


}

export async function getSuggestedWorkout(user){

}