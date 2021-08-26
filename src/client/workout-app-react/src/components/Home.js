import { useEffect, useState } from "react";
import { Link } from 'react-router-dom';
import { getSuggestedWorkout } from "../services/workouts.js";
import UserWorkoutCatalogueCard from "./workoutCards/UserWorkoutCatalogueCard.js";
import Error from "./Error.js";


function Home() {
    // delete once can get HTML requests
    const tempworkout = {
        workoutId: "1",
        workoutName: "Dance!",
        imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
        categoryId: "3"
    }
    //blank workout
    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "https://image.shutterstock.com/image-photo/404-not-found-slate-inscription-600w-175568471.jpg",
        categoryId: ""
    }
    const [errors, setErrors] = useState();
    const [workout, setWorkout] = useState(tempworkout);

// uncomment when ready for http requests
    // useEffect(() => {
    //     getSuggestedWorkout()
    //     .then(json => setWorkout(json))
    //     .catch(errs => setErrors(errs))
    // }, [])

    //if user equals admin return <h1> Welcome Admin </h1> or something like this
    return (
        <div className="container">
            <h1>Workout Buddy</h1>
            <div className="divider"></div>
            <h4>Suggested Workout</h4>
            <Error errorMessages={errors}/>
            <div className="row">
                <UserWorkoutCatalogueCard workout={workout} />
                <div className="col s12 m6 l4 xl4">
                    <div className="card small">
                        <div className="card-image ">
                            <img src="https://images.freeimages.com/images/large-previews/2f6/swept-sky-1406631.jpg" />
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