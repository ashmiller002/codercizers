import { useEffect, useState, useRef } from 'react';
import { getWorkoutHistory } from '../services/workouts.js';
import Error from './Error.js';
import UserWorkoutHistoryCard from './workoutCards/UserWorkoutHistoryCard.js'
import { gsap, Power3 } from "gsap";
import "./WorkoutHistory.css";

function WorkoutHistory({ userId }) {


    const [userWorkouts, setUserWorkouts] = useState();
    const [errors, setErrors] = useState();
    let storedUserId;
    let headerText = useRef(null);
    let cardMovement = useRef(null);
    let popIn = useRef(null);

    useEffect(() => {
        gsap.to(popIn, { duration: 0, css: { visibility: "visible" } });
        gsap.from(headerText, {
            delay: 0.1,
            duration: .1,
            opacity: 0,
            x: 30,
            ease: Power3.easeIn,
          });
          gsap.from(cardMovement, {
            delay: 0.2,
            duration: .2,
            opacity: 0,
            y: 60,
            scale: 1.5,
            ease: Power3.easeIn
          });
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
        <div className="container" id="pageContainer">
            <div ref={(el) => (popIn = el)} className="pageContainer">
                <h2 ref={(el) => (headerText = el)} >Workout History</h2>
                <div className="divider"></div>
                <Error errorMessages={errors} />
                <div ref={(el) => (cardMovement = el)} className="row">
                    {userWorkouts !== undefined && userWorkouts.map(w => {
                        return <UserWorkoutHistoryCard key={w.userWorkoutId} userWorkout={w} />
                    })}
                </div>
            </div>
        </div>
    )
}

export default WorkoutHistory;

