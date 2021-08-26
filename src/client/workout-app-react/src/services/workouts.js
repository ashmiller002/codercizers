const url="http://localhost:8080";
const token = localStorage.getItem('jwt_token');

export async function getWorkoutsByCategoryId(categoryId) {
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };

    fetch(`${url}/workouts/category/${categoryId}`, init)
        .then(response => {
            if (response.status !== 200) {
                return Promise.reject(["Suggested workout fetch failed"]);
            }
            return response.json();
        })
        


}

export async function getSuggestedWorkout(user){
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };
    fetch(`${url}/workouts/suggestedworkout/${user.loginId}`, init)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject(["Suggested workout fetch failed"]);
        }
        return response.json();
    })
}
}

export async function getWorkoutByWorkoutId(workoutId) {
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };
    fetch(`${url}/workouts/${workoutId}`, init)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject(["Current workout fetch failed"]);
        }
        return response.json();
    })
}

export async function addWorkoutToUserHistory(workoutId, userId) {
    const init = {
        method: "PUT",
        headers: {
            "Authorization":  `Bearer ${token}`
        },
        body: JSON.stringify({
            workoutId: "1"
        })
    };
    fetch(`${url}/workouts/${userId}`, init)
    .then(response => {
        if (response.status !== 201) {
            return Promise.reject(["Could not add workout to user history"]);
        }
        return response.json();
    })


}