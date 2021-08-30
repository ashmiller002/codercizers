import { useContext, useEffect, useState } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { addWorkoutToUserHistory, getWorkoutByWorkoutId } from '../services/workouts';
import Error from './Error';
import CurrentWorkoutCard from './workoutCards/CurrentWorkoutCard';

function CurrentWorkout() {

    const history = useHistory();

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: ""
    }

    const auth = useContext(LoginContext);
    const { workoutid } = useParams();
    const [errors, setErrors] = useState();
    const [workout, setWorkout] = useState(blankWorkout);
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();

    const workoutDate = `${yyyy}-${mm}-${dd}`;


    useEffect(() => {
        getWorkoutByWorkoutId(workoutid)
            .then(data => {
                setWorkout(data);
            })
            .catch(err => {
                console.log(err);
                //setErrors(err);
            })
    }, [])

    function handleSubmit(evt) {
        console.log(workoutid)
        addWorkoutToUserHistory(workoutid, auth.fullUser.userId, workoutDate)
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