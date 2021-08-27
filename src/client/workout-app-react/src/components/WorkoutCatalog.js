import './WorkoutCatalog.css';
import { useState } from 'react';
import Error from './Error';
import { getWorkoutsByCategoryId } from '../services/workouts.js';

function WorkoutCatalog() {

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: "1",
        workoutStatus:"enable"
    }
//delete tempworkout
    const tempworkout = {
        workoutId: "1",
        workoutName: "Dance!",
        imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
        categoryId: "3",
        workoutStatus:"enable"
    }

    function handleSelectCategory(evt) {
        getWorkoutsByCategoryId(evt.target.value)
        .then(json =>
            setWorkouts(json)
        )
        .catch(err =>
            setErrors(err))
    }
    const [ errors, setErrors ] = useState();
    const [ workouts, setWorkouts ] = useState([tempworkout]);

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
            <div className="divider"></div>
            <Error errorMessages={errors} />
            <div>
                
            </div>
        </div>

    )
}

export default WorkoutCatalog;