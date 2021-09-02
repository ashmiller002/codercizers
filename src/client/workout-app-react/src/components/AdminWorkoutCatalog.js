import { useEffect, useState } from "react";
import { getAllWorkouts } from "../services/workouts";
import Error from "./Error";
import AdminWorkoutCards from "./workoutCards/AdminWorkoutCards.js"

function AdminWorkoutCatalogue() {

    const blankWorkout = {
        workoutId: 0,
        workoutName: "",
        imageUrl: "",
        categoryId: 0,
        workoutStatus: "enable"
    }

    const [errors, setErrors] = useState();
    const [workouts, setWorkouts] = useState([blankWorkout]);


    useEffect(() => {
        getAllWorkouts()
            .then(json => {
                setWorkouts(json);
            })
            .catch(err => {
                setErrors(err);
            })
    }, [])

    return (
        // Within this WorkoutCatalogue, admins can enable/disable a WorkoutCatalogue, or select to edit it.  
        <div className="container">
            <h2>Edit and Disable/Enable Workouts</h2>
            <Error errorMessages={errors} />
            <div className="row">
                {workouts.map(w => {
                    return <AdminWorkoutCards key={w.workoutId} currentworkout={w} />
                })}
            </div>
        </div>

    )
}

export default AdminWorkoutCatalogue;