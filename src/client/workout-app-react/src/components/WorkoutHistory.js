import { useEffect, useState } from 'react';
import { getWorkoutHistory } from '../services/workouts.js';
import Error from './Error.js';
import UserWorkoutHistoryCard from './workoutCards/UserWorkoutHistoryCard.js'

function WorkoutHistory({ userId }) {


    const [userWorkouts, setUserWorkouts] = useState();
    const [errors, setErrors] = useState();
    let storedUserId;

    useEffect(() => {
        if (userId !== 0) {
            localStorage.setItem('user_id', userId);
            // eslint-disable-next-line react-hooks/exhaustive-deps
            storedUserId = userId;
        }
        if (userId === 0) {
            // eslint-disable-next-line react-hooks/exhaustive-deps
            storedUserId = localStorage.getItem('user_id');
        }
        getWorkoutHistory(storedUserId)
            .then(data => {
                setUserWorkouts(data);

            })
            .catch(err => {
                setErrors(err);
            })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, []);

    useEffect(() => {
        if (userWorkouts !== undefined) {
            sortWorkoutsByDate();
        }
// eslint-disable-next-line react-hooks/exhaustive-deps
    }, [userWorkouts])

    function sortWorkoutsByDate() {
        const workoutCopy = [...userWorkouts];
        const sortedWorkouts = workoutCopy.sort((b, a) => {
            return a.userWorkoutId - b.userWorkoutId;
        })
        if (!(sortedWorkouts[0] === userWorkouts[0])) {
            setUserWorkouts(sortedWorkouts);
        }

    }

    return (
        <div className="container">
            <h2>Workout History</h2>
            <div className="divider"></div>
            <Error errorMessages={errors} />
            <div className="row">
                {userWorkouts !== undefined && userWorkouts.map(w => {
                    return <UserWorkoutHistoryCard key={w.userWorkoutId} userWorkout={w} />
                })}
            </div>
        </div>
    )
}

export default WorkoutHistory;

