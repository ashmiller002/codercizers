import './WorkoutCatalog.css';
import { useState, useRef, useEffect } from 'react';
import Error from './Error';
import { getWorkoutsByCategoryId } from '../services/workouts.js';
import UserWorkoutCatalogueCard from './workoutCards/UserWorkoutCatalogueCard.js';
import { gsap, Power3 } from "gsap";

function WorkoutCatalog() {

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: "0",
        workoutStatus: "disable"
    }

    const [errors, setErrors] = useState();
    const [workouts, setWorkouts] = useState([blankWorkout]);

    let headerText = useRef(null);
    let button1 = useRef(null);
    let button2 = useRef(null);
    let button3 = useRef(null);
    let button4 = useRef(null);
    let button5 = useRef(null);
    let cardMovement = useRef(null);
    let popIn = useRef(null);

    useEffect(() => {
        gsap.to(popIn, { duration: 0, css: { visibility: "visible" } });
        gsap.from(headerText, {
            delay: 0.1,
            duration: .5,
            opacity: 0,
            x: 30,
            ease: Power3.easeIn,
          });
          gsap.from(button1, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
          gsap.from(button2, {
            delay: 0.23,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
          gsap.from(button3, {
            delay: 0.26,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
          gsap.from(button4, {
            delay: 0.28,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
          gsap.from(button5, {
            delay: 0.3,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
          gsap.from(cardMovement, {
            delay: 0.3,
            duration: .5,
            opacity: 0,
            y: 60,
            button1
          });
    }, [])

    function handleSelectCategory(evt) {
        const categoryId = parseInt(evt.target.value, 10);
        getWorkoutsByCategoryId(categoryId)
            .then((data) => {
                const enabledWorkouts = filterDisabledWorkouts(data)
                setWorkouts(enabledWorkouts);
            }
            )
            .catch(err =>
                setErrors(err))
    }

    function filterDisabledWorkouts(allWorkouts) {
        return allWorkouts.filter(w => w.workoutStatus === "enable");
    }


    return (
        <div ref={(el) => (popIn = el)}className="container" id="pageContainer">
            <h2  ref={(el) => (headerText = el)} className="workoutCataloug">Workout Catalog</h2>
            <div className="divider"></div>
            <div className="categorySelectors">
                <button type="button" onClick={handleSelectCategory} value="1" className="btn-large" ref={(el) => (button1 = el)}>Upper Body Strength</button>
                <button type="button" onClick={handleSelectCategory} value="2" className="btn-large" ref={(el) => (button2 = el)}>Lower Body Strength</button>
                <button type="button" onClick={handleSelectCategory} value="3" className="btn-large" ref={(el) => (button3 = el)}>Cardio</button>
                <button type="button" onClick={handleSelectCategory} value="4" className="btn-large" ref={(el) => (button4 = el)}>Mobility</button>
                <button type="button" onClick={handleSelectCategory} value="5" className="btn-large" ref={(el) => (button5 = el)}>Rest Day</button>
            </div>
            <Error errorMessages={errors} />
            <div ref={(el) => (cardMovement = el)} className="row">
                {workouts !== undefined && workouts.map(w => {
                    return <UserWorkoutCatalogueCard workout={w} />
                })}
            </div>
        </div>

    )
}

export default WorkoutCatalog;