import { useEffect, useState } from "react";
import { Link, useHistory, useParams } from "react-router-dom";
import { editWorkoutById, getWorkoutByWorkoutId } from "../services/workouts";
import Error from "./Error";
import "./AddEditWorkout.css";

function AddEditWorkout() {
    const blankWorkout = {
        workoutId: "0",
        workoutName: "",
        imageUrl: "",
        categoryId: "0",
        workoutStatus: "disable"
    }

    const { id } = useParams();
    const [errors, setErrors] = useState();
    let ableToSubmit = true;
    const [method, setMethod] = useState("Add");
    const history = useHistory();



    useEffect(() => {
        const pathname = window.location.pathname;
        if (pathname.includes("edit")) {
            setMethod("Edit");
            getWorkoutByWorkoutId(id)
                .then(data => {
                    setWorkout(data);
                    ableToSubmit = true;
                })
                .catch(err => {
                    setErrors(err);
                    ableToSubmit = false;
                });
        }
        if (pathname.includes("add")) {
            setMethod("Add");
        }
    }, [history])

    function handleChange(evt) {
        const nextWorkout = { ...workout };
        nextWorkout[evt.target.name] = evt.target.value;
        setWorkout(nextWorkout);
    }

    function handleChangeCategory(evt) {
        const nextWorkout = { ...workout };
        if (evt.target.checked) {
            nextWorkout.categoryId = parseInt(evt.target.value);
            setWorkout(nextWorkout);
        }
    }

    function handleClick(evt) {
        evt.preventDefault();
        if (!ableToSubmit) {
            return;
        }
        if (method === "Edit") {
            editWorkoutById(workout)
            .then(history.push("/adminworkoutcatalog"))
            .catch(err => {
                setErrors(err);
            })
        } else if (method === "Add") {

        }


    }


    const [workout, setWorkout] = useState(blankWorkout);

    return (
        <div className="container">
            <h2>{method} Workout</h2>
            <Error errorMessages={errors} />
            <form class="addEditWorkout" onSubmit={handleClick}>
                <div className="row">
                    <label htmlFor="workoutName">WorkoutName</label>
                    <input type="text" id="workoutName" name="workoutName" value={workout.workoutName} onChange={handleChange} />
                </div>
                <div className="row">
                    <label htmlFor="imageUrl">Workout Image Url</label>
                    <input type="text" id="imageUrl" name="imageUrl" value={workout.imageUrl} onChange={handleChange} />
                </div>
                <div className="category">
                    <label><div className="radioPrompt">Category</div>
                        <input className="with-gap" name="category" value="1" type="radio" required checked={workout.categoryId === 1} onChange={handleChangeCategory} />
                        <span>Upper Body Strength</span>
                    </label><br />
                    <label>
                        <input className="with-gap" name="category" value="2" type="radio" checked={workout.categoryId === 2} onChange={handleChangeCategory} />
                        <span>Lower Body Strength</span>
                    </label><br />
                    <label>
                        <input className="with-gap" name="category" value="3" type="radio" checked={workout.categoryId === 3} onChange={handleChangeCategory} />
                        <span>Cardio</span>
                    </label><br />
                    <label>
                        <input className="with-gap" name="category" value="4" type="radio" checked={workout.categoryId === 4} onChange={handleChangeCategory} />
                        <span>Mobility</span>
                    </label><br />
                    <label>
                        <input className="with-gap" name="category" value="5" type="radio" checked={workout.categoryId === 5} onChange={handleChangeCategory} />
                        <span>Rest Day</span>
                    </label>
                </div >
                <div>
                    <button type="submit" className="btn">{method}</button>
                    <Link to="/adminworkoutcatalog" type="button" className="btn cancel">Cancel</Link>
                </div>
            </form >
        </div >
    )
}

export default AddEditWorkout;