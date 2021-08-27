import { useState } from 'react';
import UserWorkoutHistoryCard from './workoutCards/UserWorkoutHistoryCard.js'

// show all workouts, even disabled ones. going to have 
// for getworkoutsByUserId http request, will get joined workouts with dates
function WorkoutHistory() {

    //delete tempworkout
    const tempworkout = {
        workoutId: "1",
        workoutName: "Dance!",
        imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
        categoryId: "3",
        workoutStatus: "enable"
    }

    const [userWorkouts, setUserWorkouts] = useState([tempworkout]);
    return (
        <div className="container">
            <h2>Workout History</h2>
            <div className="divider"></div>
            <div className="row">
                <UserWorkoutHistoryCard workout={userWorkouts[0]} />
                {/* {userWorkouts !== undefined && userWorkouts.map(w => {
                    return <UserWorkoutHistoryCard workout={w} />
                })} */}
            </div>
        </div>
    )
}

export default WorkoutHistory;

