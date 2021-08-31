import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import { getSuggestedWorkout } from "../services/workouts.js";
import UserWorkoutCatalogueCard from "./workoutCards/UserWorkoutCatalogueCard.js";
import Error from "./Error.js";


function Home({userId}) {

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: "",
        workoutStatus: "enable"
    }
    const [errors, setErrors] = useState();
    const [workout, setWorkout] = useState(blankWorkout);

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
        getSuggestedWorkout(storedUserId)
            .then(json => {setWorkout(json)})
            .catch(errs => {
                setErrors(errs)
            })
    }, [])

    return (
        <div className="container">
            <h1>Workout Buddy</h1>
            <div className="divider"></div>
            <h4>Suggested Workout</h4>
            <Error errorMessages={errors} />
            <div className="row">
                <UserWorkoutCatalogueCard workout={workout} />
                <div className="col s12 m6 l4 xl4">
                    <div className="card small">
                        <div className="card-image ">
                            <img src="https://images.freeimages.com/images/large-previews/2f6/swept-sky-1406631.jpg" alt="blue sky" />
                        </div>
                        <div className="card-content ">
                            <p><b>Browse Other Workouts</b></p>
                        </div>
                        <div className="sticky-action">
                            <Link to="/workoutcatalog" className="btn-small">Select</Link>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    )
}

export default Home;