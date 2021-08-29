import { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./UserNavBar.css";
import LoginContext from "../contexts/LoginContext";

function AdminNavBar() {
const [adminView, setAdminView] = useState();
const auth = useContext(LoginContext);

function handleClick(evt) {
    if (evt.target.id === "logout") {
        setAdminView();
    }
    setAdminView(evt.target.id);
}

    const blank = ["", "", "", "", "", "", "",];
    let selected = [...blank];
    if (adminView === "home" || adminView === undefined) {
        let selectedCopy = blank;
        selectedCopy[0] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (adminView === "adminWorkoutCatalog") {
        let selectedCopy = blank;
        selectedCopy[1] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (adminView === "addWorkout") {
        let selectedCopy = blank;
        selectedCopy[2] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }


    if (adminView === "login") {
        let selectedCopy = blank;
        selectedCopy[5] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (adminView === "register") {
        let selectedCopy = blank;
        selectedCopy[6] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }



    return (
        <nav id="navbar" >
            <Link to="/" className={"btn " + selected[0]} id="home" onClick={handleClick}>Home</Link>
            <Link  to="/adminworkoutcatalog" className={"btn " + selected[1]} id="adminWorkoutCatalog" onClick={handleClick}>Workout Catalog</Link>
            <Link to="/addworkout" className={"btn " + selected[2]} id="addWorkout" onClick={handleClick}>Add Workout</Link>

            {/* {auth.user == null &&
                <Link to="/login" className={"btn " + selected[5]} id="login" onClick={handleClick}>Login</Link>
            } */}
            {auth.user !== null &&
                <button type="button" className={"btn"} id="logout" onClick={auth.logout}>Logout</button>
            }
            {/* <Link to="/register" className={"btn " + selected[6]} id="register" onClick={handleClick}>Register</Link> */}

            {auth.user !== null &&
                <span>Hello,&nbsp;{auth.user[0]}</span>
            }
        </nav>
    )
}

export default AdminNavBar;