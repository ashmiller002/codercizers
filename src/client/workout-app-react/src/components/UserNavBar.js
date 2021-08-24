import { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./UserNavBar.css";

function UserNavBar() {

    const [view, setView] = useState();
    const blank = ["", "", "", "", "", "", "",];
    let selected = [...blank];
    if (view === "agent") {
        let selectedCopy = blank;
        selectedCopy[0] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (view === "alias") {
        let selectedCopy = blank;
        selectedCopy[1] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (view === "agency") {
        let selectedCopy = blank;
        selectedCopy[2] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (view === "location") {
        let selectedCopy = blank;
        selectedCopy[3] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (view === "logout") {
        let selectedCopy = blank;
        selectedCopy[4] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (view === "login") {
        let selectedCopy = blank;
        selectedCopy[5] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (view === "register") {
        let selectedCopy = blank;
        selectedCopy[6] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    function handleClick(evt) {
        if (evt.target.id === "logout") {
            setView();
        }
        setView(evt.target.id);
    }

    return (
        <div id="navbar" >
            <p>Workout Buddy</p>
            <Link  className={"btn " + selected[0]} id="workoutCatalogue" onClick={handleClick}>Workout Catalog</Link>
            <Link  className={"btn " + selected[1]} id="workoutHistory" onClick={handleClick}>Workout History</Link>
            <Link  className={"btn " + selected[2]} id="addExternalWorkout" onClick={handleClick}>Add External Workout</Link>
            <Link  className={"btn " + selected[3]} id="account" onClick={handleClick}>Account</Link>
            <Link  className={"btn " + selected[4]} id="logout" onClick={handleClick}>Logout</Link>
            {/* {auth.user == null &&
                <Link to="/login" className={"btn " + selected[5]} id="login" onClick={handleClick}>Login</Link>
            }
            {auth.user !== null &&
                <button type="button" className={"btn"} id="logout" onClick={auth.logout}>Logout</button>
            }
            <Link to="/register" className={"btn " + selected[6]} id="register" onClick={handleClick}>Register</Link>}

            {auth.user !== null &&
                <span>Hello,&nbsp;{auth.user}</span>
            } */}
        </div>
    )
}

export default UserNavBar;