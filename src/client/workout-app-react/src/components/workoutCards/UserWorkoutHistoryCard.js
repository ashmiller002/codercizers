// like workoutCard but also has the date attached in the object passed and is used in workout history. doesn't matter if workout is enabled or disabled

import './Cards.css'

function UserWorkoutHistoryCard({ userWorkout }) {
    function getCategory() {
        if (userWorkout === undefined) {
            return "Unknown";
        }
        switch (userWorkout.workout.categoryId) {
            case 1: return "Upper Body Strength"
            case 2: return "Lower Body Strength"
            case 3: return "Cardio"
            case 4: return "Mobility"
            case 5: return "Rest Day"
            default: return "Unknown"
        }
    }

    const realCategory = getCategory();

    return (
        <div className="col s12 m6 l4 xl4">
            <div className="card small">
                <div className="card-image">
                    <img src={userWorkout.workout.imageUrl} alt="workout"/>
                </div>
                <div className="card-content">
                    <p><b>Name: </b>{userWorkout.workout.workoutName}</p>
                    <p><b>Category: </b>{realCategory}</p>
                    <p><b>Date: </b>{userWorkout.workoutDate}</p>
                </div>
            </div>
        </div>
    );
}

export default UserWorkoutHistoryCard;