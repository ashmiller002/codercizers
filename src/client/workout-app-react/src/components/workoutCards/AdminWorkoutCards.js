import { Link } from 'react-router-dom';
import { useState } from 'react';
import { editWorkoutById } from '../../services/workouts';

//these workout cards will have an edit button as well as an enable/disable button
function AdminWorkoutCards( {currentworkout} ) {
    
    const [workout, setWorkout] = useState(currentworkout);
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

    function disableWorkout() {
        const newWorkout = {...workout};
        newWorkout.workoutStatus = "disable";
        editWorkoutById(newWorkout)
        .then(() => {
            setWorkout(newWorkout);
        })
        .catch(console.log);

    }
    
    function enableWorkout() {
        const newWorkout = {...workout};
        newWorkout.workoutStatus = "enable";
        setWorkout(newWorkout);
        editWorkoutById(newWorkout)
        .then(() => {
            setWorkout(newWorkout);
        })
        .catch(console.log);
    }

    if (workout.workoutStatus === "disable") {
        return (
            <div className="col s12 m6 l4 xl4">
            <div className="card small disabled">
                <div className="card-image">
                    <img src={workout.imageUrl} />
                </div>
                <div className="card-content">
                    <p><b>Name: </b>{workout.workoutName}</p>
                    <p><b>Category: </b>{realCategory}</p>
                </div>
                <div className="sticky-action">
                    <Link to={`/editworkout/${workout.workoutId}`} className="btn-small">Edit</Link>
                    <button className="btn-small cancel" onClick={enableWorkout}>Enable</button>
                </div>
            </div>
        </div>
        )
    }

    return (
        <div className="col s12 m6 l4 xl4">
            <div className="card small">
                <div className="card-image">
                    <img src={workout.imageUrl} />
                </div>
                <div className="card-content">
                    <p><b>Name: </b>{workout.workoutName}</p>
                    <p><b>Category: </b>{realCategory}</p>
                </div>
                <div className="sticky-action">
                    <Link to={`/editworkout/${workout.workoutId}`} className="btn-small">Edit</Link>
                    <button className="btn-small red darken-4 disable" onClick={disableWorkout}>Disable</button>
                </div>
            </div>
        </div>
    );
}

export default AdminWorkoutCards;