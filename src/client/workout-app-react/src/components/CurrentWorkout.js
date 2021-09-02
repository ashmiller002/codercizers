import { useContext, useEffect, useState, useRef } from 'react';
import { useHistory, useParams } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { addWorkoutToUserHistory, getWorkoutByWorkoutId } from '../services/workouts';
import Error from './Error';
import CurrentWorkoutCard from './workoutCards/CurrentWorkoutCard';
import { gsap, Power3 } from "gsap";

function CurrentWorkout() {

    const history = useHistory();

    const blankWorkout = {
        workoutId: "",
        workoutName: "",
        imageUrl: "",
        categoryId: ""
    }

    const auth = useContext(LoginContext);
    const { workoutid } = useParams();
    const [errors, setErrors] = useState();
    const [workout, setWorkout] = useState(blankWorkout);
    const today = new Date();
    const dd = String(today.getDate()).padStart(2, '0');
    const mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    const yyyy = today.getFullYear();

    const workoutDate = `${yyyy}-${mm}-${dd}`;

    let headerText = useRef(null);
    let cardMovement= useRef(null);


    useEffect(() => {
        gsap.from(headerText, {
            delay: 0.1,
            duration: .5,
            opacity: 0,
            x: 30,
            ease: Power3.easeIn,
          });
          gsap.from(cardMovement, {
            delay: 0.2,
            duration: .5,
            opacity: 0,
            y: 60,
            cardMovement
          });
        getWorkoutByWorkoutId(workoutid)
            .then(data => {
                setWorkout(data);
            })
            .catch(err => {
                setErrors(err);
            })
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    function handleSubmit(evt) {
        console.log(workoutid)
        addWorkoutToUserHistory(workoutid, auth.fullUser.userId, workoutDate)
            .then(() => {
                history.push("/workouthistory")
            })
            .catch(errs => {
                setErrors([errs])
            })
    }

    return (
        <div className="container">
            <h2 ref={(el) => (headerText = el)}>Current Workout</h2>
            <Error errorMessages={errors} />
            <div ref={(el) => (cardMovement = el)} className="row">
                <CurrentWorkoutCard workout={workout} handleSubmit={handleSubmit} />
            </div>
        </div>
    );
}

export default CurrentWorkout;