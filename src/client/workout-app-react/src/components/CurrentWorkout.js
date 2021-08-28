import { useContext, useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import FullUserContext from '../contexts/FullUserContext';
import { addWorkoutToUserHistory } from '../services/workouts';
import Error from './Error';
import CurrentWorkoutCard from './workoutCards/CurrentWorkoutCard';

function CurrentWorkout() {

    const history = useHistory();
    const fullUser = useContext(FullUserContext);

    // temp workout: delete when app is working

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: ""
    }

    const { workoutid } = useParams();
    const [errors, setErrors] = useState();
    const [workout, setWorkout] = useState(blankWorkout);

    // use http request getworkout by workout id
    useEffect(() => {
        
    })

    //function to add to user workout history
    function handleSubmit(submittedWorkoutId,) {
        addWorkoutToUserHistory("1", "2")
            .then(() => {
                history.push("/workouthistory")
            })
            .catch(errs => {
                setErrors([errs])
            })
    }

    return (
        <div className="container">
            <h2>Current Workout</h2>
            <Error errorMessages={errors} />
            <div className="row">
                <CurrentWorkoutCard workout={workout} handleSubmit={handleSubmit} />
            </div>
        </div>
    );
}

export default CurrentWorkout;