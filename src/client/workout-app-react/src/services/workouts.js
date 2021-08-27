const url="http://localhost:8080";


export async function getWorkoutsByCategoryId(categoryId) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };

    fetch(`${url}/api/workout/category/${categoryId}`, init)
        .then(response => {
            if (response.status !== 200) {
                return Promise.reject(["workouts fetch failed"]);
            }
            return response.json();
        })
        


}

export async function getSuggestedWorkout(user){
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization":  `Bearer ${token}`
        }
    };
    fetch(`${url}/workout/suggestedworkout/${user.loginId}`, init)
    .then(response => {
        if (response.status !== 200) {
            return Promise.reject(["Suggested workout fetch failed"]);
        }
        return response.json();
    })
}


export async function getWorkoutByWorkoutId(workoutId) {
    const token = localStorage.getItem('jwt_token');
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
    const token = localStorage.getItem('jwt_token');
    const init = {
        method: "PUT",
        headers: {
            "Authorization":  `Bearer ${token}`
        },
        body: JSON.stringify({
            workoutId: "1"
        })
    };
    fetch(`${url}/workout/${userId}`, init)
    .then(response => {
        if (response.status !== 201) {
            return Promise.reject(["Could not add workout to user history"]);
        }
        return response.json();
    })


}