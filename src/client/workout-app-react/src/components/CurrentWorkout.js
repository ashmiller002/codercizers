import { useState } from 'react';
import { useParams } from 'react-router-dom';
import Error from './Error';
import CurrentWorkoutCard from './workoutCards/CurrentWorkoutCard';

function CurrentWorkout() {

        // temp workout: delete when app is working
        const tempworkout = {
            workoutId: "1",
            workoutName: "Dance!",
            imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
            categoryId: "3"
        }
    
        const blankWorkout = {
            workoutId: "",
            workoutName: "",
            imageUrl: "https://image.shutterstock.com/image-photo/404-not-found-slate-inscription-600w-175568471.jpg",
            categoryId: ""
        }

    const { workoutid } = useParams();
    const [ errors, setErrors ] = useState();
    const [ workout, setWorkout ] = useState(tempworkout);

    // use http request getworkout by workout id

    return(
        <div className="container">
            <h2>Current Workout</h2>
            <Error errorMessages={errors}/>
            <div className="row">
                <CurrentWorkoutCard workout={workout}/>
            </div>
        </div>
    );
}

export default CurrentWorkout;