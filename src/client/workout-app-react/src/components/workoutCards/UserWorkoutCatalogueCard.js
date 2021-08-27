import { Link } from 'react-router-dom';
import Error from '../Error';
import './Cards.css'

// used in workout catalogue. Also used on User Home page. has select button that lists as current workout.

function UserWorkoutCatalogueCard({ workout }) {

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
    if (workout.workoutStatus === "disable") {
        return (
            <div></div>
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
                    <Link to={`/currentworkout/${workout.workoutId}`} className="btn-small">Select</Link>
                </div>
            </div>
        </div>
    );
}

export default UserWorkoutCatalogueCard;