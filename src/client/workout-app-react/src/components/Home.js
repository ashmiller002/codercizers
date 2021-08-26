import WorkoutCard from "./workoutCards/UserWorkoutCatalogueCard.js";

// These workouts are going to be used
function Home() {
    const workout={
        workoutId: "1",
        workoutName: "Dance!",
        imageUrl: "https://media1.popsugar-assets.com/files/thumbor/3ZSWfYrYxA4Fg6R1KRIuRqqQd4Q/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2019/07/16/731/n/1922729/85d1ce265d2dfc639070a3.22857928_/i/25-Minute-Total-Body-Strength-Workout.jpg",
        categoryId: "3"
    }
    //if user equals admin return <h1> Welcome Admin </h1> or something like this
    return (
        <div className="container">
            <div className="row">
                <WorkoutCard workout={workout}/>
            </div>

        </div>
    )
}

export default Home;