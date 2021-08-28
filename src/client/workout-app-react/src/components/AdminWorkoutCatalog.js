import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
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
    //delete tempworkout
    const tempworkout = {
        workoutId: 1,
        workoutName: "Dance!",
        imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
        categoryId: 3,
        workoutStatus: "enable"
    }
    const history = useHistory();
    const [errors, setErrors] = useState();
    const [workouts, setWorkouts] = useState([tempworkout]);


    useEffect(() => {
        getAllWorkouts()
            .then(json => {
                setWorkouts(json);
            })
            .catch(err => { console.log(err) })
    }, [])

    return (
        // Within this WorkoutCatalogue, admins can enable/disable a WorkoutCatalogue, or select to edit it.  
        <div className="container">
            <h2>Edit and Disable/Enable Workouts</h2>
            <Error errorMessages={errors} />
            <div className="row">
                {workouts.map(w => {
                    return <AdminWorkoutCards key={w.workoutId} workout={w} />
                })}
            </div>
        </div>

    )
}

export default AdminWorkoutCatalogue;