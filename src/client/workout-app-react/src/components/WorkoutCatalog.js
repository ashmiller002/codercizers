import './WorkoutCatalog.css';
import { useState } from 'react';
import Error from './Error';
import { getWorkoutsByCategoryId } from '../services/workouts.js';
import UserWorkoutCatalogueCard from './workoutCards/UserWorkoutCatalogueCard.js';

function WorkoutCatalog() {

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: "0",
        workoutStatus: "disable"
    }

    const [errors, setErrors] = useState();
    const [workouts, setWorkouts] = useState([blankWorkout]);

    function handleSelectCategory(evt) {
        const categoryId = parseInt(evt.target.value, 10);
        getWorkoutsByCategoryId(categoryId)
            .then((data) => {
                const enabledWorkouts = filterDisabledWorkouts(data)
                setWorkouts(enabledWorkouts);
            }
            )
            .catch(err =>
                setErrors(err))
    }

    function filterDisabledWorkouts(allWorkouts) {
        return allWorkouts.filter(w => w.workoutStatus === "enable");
    }


    return (
        <div className="container">
            <h2>Workout Catalog</h2>
            <div className="divider"></div>
            <div className="categorySelectors">
                <button type="button" onClick={handleSelectCategory} value="1" className="btn-large">Upper Body Strength</button>
                <button type="button" onClick={handleSelectCategory} value="2" className="btn-large">Lower Body Strength</button>
                <button type="button" onClick={handleSelectCategory} value="3" className="btn-large">Cardio</button>
                <button type="button" onClick={handleSelectCategory} value="4" className="btn-large">Mobility</button>
                <button type="button" onClick={handleSelectCategory} value="5" className="btn-large">Rest Day</button>
            </div>
            <Error errorMessages={errors} />
            <div className="row">
                {workouts !== undefined && workouts.map(w => {
                    return <UserWorkoutCatalogueCard workout={w} />
                })}
            </div>
        </div>

    )
}

export default WorkoutCatalog;