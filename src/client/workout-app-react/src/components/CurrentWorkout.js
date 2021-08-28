import { useContext, useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import FullUserContext from '../contexts/FullUserContext';
import { addWorkoutToUserHistory, getWorkoutByWorkoutId } from '../services/workouts';
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

    useEffect(() => {
        getWorkoutByWorkoutId(workoutid)
            .then(data => {
                setWorkout(data);
            })
            .catch(err => {
                console.log(err);
                //setErrors(err);
            })
    })

    function handleSubmit(workoutId, userId) {
        addWorkoutToUserHistory(workoutId, userId)
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