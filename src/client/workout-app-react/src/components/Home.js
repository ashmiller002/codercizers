import { useEffect, useState, useRef } from "react";
import { Link } from 'react-router-dom';
import { getSuggestedWorkout } from "../services/workouts.js";
import UserWorkoutCatalogueCard from "./workoutCards/UserWorkoutCatalogueCard.js";
import Error from "./Error.js";
import { gsap, Power3 } from "gsap";
import "./Home.css";


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
    
    let headerText = useRef(null);
    let subHeaderText = useRef(null);
    let cardMovement = useRef(null);
    let cardMovement2 = useRef(null);
    let popIn = useRef(null);
    let storedUserId;

    useEffect(() => {
        gsap.to(popIn, { duration: 0, css: { visibility: "visible" } });
        gsap.from(headerText, {
            delay: 0.1,
            duration: .5,
            opacity: 0,
            x: 30,
            ease: Power3.easeIn,
          });
          gsap.from(subHeaderText, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            x: -30,
            ease: Power3.easeIn,
          });
          gsap.from(cardMovement, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            y: 70,
            ease: Power3.easeIn,
          });
          gsap.from(cardMovement2, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            y: 70,
            ease: Power3.easeIn,
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
        getSuggestedWorkout(storedUserId)
            .then(json => {setWorkout(json)})
            .catch(errs => {
                setErrors(errs)
            })  
    }, [])

    return (
        <div ref={(el) => (popIn = el)}className="container" id="pageContainer">
            <h1 className="header" ref={(el) => (headerText = el)}>Workout Buddy</h1>
            <div className="divider"></div>
            <h4 ref={(el) => (subHeaderText = el)}>Suggested Workout</h4>
            <Error errorMessages={errors} />
            <div className="row" ref={(el) => (cardMovement2 = el)}>
                <UserWorkoutCatalogueCard workout={workout} />
                <div className="col s12 m6 l4 xl4">
                    <div ref={(el) => (cardMovement = el)} className="card small">
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