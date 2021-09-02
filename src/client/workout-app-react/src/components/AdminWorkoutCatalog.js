import { useEffect, useState,useRef } from "react";
import { getAllWorkouts } from "../services/workouts";
import Error from "./Error";
import AdminWorkoutCards from "./workoutCards/AdminWorkoutCards.js"
import { gsap, Power3 } from "gsap";


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
    let cardMovement = useRef(null);
    let popIn = useRef(null);

    useEffect(() => {
        gsap.to(popIn, { duration: 0, css: { visibility: "visible" } });
        gsap.from(cardMovement, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            y: 60,
            cardMovement
        });
        
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
        <div ref={(el) => (popIn = el)} className="container" id="adminPage">
            <h2>Edit and Disable/Enable Workouts</h2>
            <Error errorMessages={errors} />
            <div ref={(el) => (cardMovement = el)} className="row">
                {workouts.map(w => {
                    return <AdminWorkoutCards key={w.workoutId} currentworkout={w} />
                })}
            </div>
        </div>

    )
}

export default AdminWorkoutCatalogue;