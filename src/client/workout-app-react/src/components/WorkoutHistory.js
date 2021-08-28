import { useEffect, useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext.js';
import { getWorkoutHistory } from '../services/workouts.js';
import Error from './Error.js';
import UserWorkoutHistoryCard from './workoutCards/UserWorkoutHistoryCard.js'
 
function WorkoutHistory() {


    const [userWorkouts, setUserWorkouts] = useState();
    const [errors, setErrors] = useState();
    const history = useHistory();
    const auth = useContext(LoginContext)

    useEffect(() => {
        getWorkoutHistory(auth.fullUser.userId)
            .then(data => {
                setUserWorkouts(data);
            })
            .catch(err => {
                console.log(err);
                //setErrors(err);
            })
    }, [history])
    return (
        <div className="container">
            <h2>Workout History</h2>
            <div className="divider"></div>
            <Error errorMessages={errors} />
            <div className="row">
                {userWorkouts !== undefined && userWorkouts.map(w => {
                    return <UserWorkoutHistoryCard workout={w} />
                })}
            </div>
        </div>
    )
}

export default WorkoutHistory;

