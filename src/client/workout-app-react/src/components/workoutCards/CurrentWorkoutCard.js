import { Link } from 'react-router-dom';
import './Cards.css'


function CurrentWorkoutCard({ workout, handleSubmit }) {

    function getCategory() {
        if (workout === undefined) {
            return "Unknown";
        }
        switch (workout.categoryId) {
            case "1": return "Upper Body Strength"
            case "2": return "Lower Body Strength"
            case "3": return "Cardio"
            case "4": return "Mobility"
            case "5": return "Rest Day"
            default: return "Unknown"
        }
    }


    const realCategory = getCategory();
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
                    <button className="btn-small" onClick={handleSubmit}>Done</button>
                    <Link to="/" className="btn-small cancel">Cancel</Link>
                </div>
            </div>
        </div>
    );
}

export default CurrentWorkoutCard;