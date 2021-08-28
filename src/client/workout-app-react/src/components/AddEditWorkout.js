import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getWorkoutByWorkoutId } from "../services/workouts";

function AddEditWorkout() {
    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: "0",
        workoutStatus: "disable"
    }

    const { id } = useParams();

    const pathname = window.location.pathname;

    useEffect(() => {
        if (pathname.includes("edit")) {
            getWorkoutByWorkoutId(id)
            .then(data => {
                setWorkout(data);
            })
            .catch(console.log);
        }
    }, [])


    const [workout, setWorkout] = useState(blankWorkout);
    //currentworkout 

    // for add workout receives blank workout as current workout. for edit, useParams to get current workout.
    return (
        <div> {workout.workoutId}</div>
    )
}

export default AddEditWorkout;