const url = "http://localhost:8080";


export async function getWorkoutsByCategoryId(categoryId) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };

    const response = await fetch(`${url}/api/workout/category/${categoryId}`, init);

    if (response.status !== 200) {
        return Promise.reject(["workouts fetch failed"]);
    }
    return await response.json();
}

export async function getSuggestedWorkout(user) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/workout/suggestedworkout/${user.loginId}`, init);
    if (response.status !== 200) {
        return Promise.reject(["Suggested workout fetch failed"]);
    }
    return await response.json();

}


export async function getWorkoutByWorkoutId(workoutId) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/api/workout/${workoutId}`, init);
    if (response.status === 404) {
        return Promise.reject(["No workout with this id found"]);
    }
    if (response.status !== 200) {
        return Promise.reject(["Current workout fetch failed"]);
    }
    return await response.json();
}

export async function addWorkoutToUserHistory(workoutId, userId) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify({
            "workoutId": workoutId
        })
    };
    const response = await fetch(`${url}/workout/${userId}`, init);
    if (response.status !== 201) {
        return Promise.reject(["Could not add workout to user history"]);
    }
    return await response.json();
}

export async function getWorkoutHistory(userId) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/workout/${userId}`, init);
    if (response.status !== 200) {
        return Promise.reject(["Could not get workout history"]);
    }
    return await response.json();
}

export async function getAllWorkouts() {
    const token = localStorage.getItem('jwt_token');
    const init = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    const response = await fetch(`${url}/api/workout`, init);
    if (response.status !== 200) {
        return Promise.reject(["Could not fetch all workouts"]);
    }
    return await response.json();
}

export async function editWorkoutById(workout) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(workout)
    };
    const response = await fetch(`${url}/api/workout/admin/${workout.workoutId}`, init);
    if (response.status !== 204) {
        const messages = await response.json();
        return Promise.reject(messages);
    }
    return;
}

export async function addWorkout(workout) {
    const token = localStorage.getItem('jwt_token');
    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(workout)
    };
    const response = await fetch(`${url}/api/workout/admin`, init);
    if (response.status !== 201) {
        const messages = await response.json();
        return Promise.reject(messages);
    }
    return;
}
