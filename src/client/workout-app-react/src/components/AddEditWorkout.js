import { useState } from "react";

function AddEditWorkout({method}) {
    const blankWorkout = {

    }

    let currentWorkout; 

    const pathname = window.location.pathname;
    if (pathname.contains("add")) {
        currentWorkout = blankWorkout;
    } else {
        //current workout equals workout fetch from workout id which you get from useParams.
    }

    //currentworkout 
    [ workout, setWorkout ] = useState(currentWorkout);
    // for add workout receives blank workout as current workout. for edit, useParams to get current workout.
    return (
        <div> add workout</div>
    )
}

export default AddEditWorkout;