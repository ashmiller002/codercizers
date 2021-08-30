// like workoutCard but also has the date attached in the object passed and is used in workout history. doesn't matter if workout is enabled or disabled

import './Cards.css'

function UserWorkoutHistoryCard({ workout }) {
    function getCategory() {
        if (workout === undefined) {
            return "Unknown";
        }
        switch (workout.categoryId) {
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
                    <img src={workout.imageUrl} alt="workout"/>
                </div>
                <div className="card-content">
                    <p><b>Name: </b>{workout.workoutName}</p>
                    <p><b>Category: </b>{realCategory}</p>
                    {/* Need to get date too */}
                </div>
            </div>
        </div>
    );
}

export default UserWorkoutHistoryCard;