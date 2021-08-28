import { useContext, useState } from "react";
import { Link } from "react-router-dom";
import "./UserNavBar.css";
import LoginContext from "../contexts/LoginContext";
import FullUserContext from "../contexts/FullUserContext";

function UserNavBar() {

    const auth = useContext(LoginContext);
    const blank = ["", "", "", "", "", "", "",];
    let selected = [...blank];
    if (window.location.pathname === "/workoutcatalog") {
        let selectedCopy = blank;
        selectedCopy[0] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (window.location.pathname === "/workouthistory") {
        let selectedCopy = blank;
        selectedCopy[1] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (window.location.pathname === "/addexternalworkout") {
        let selectedCopy = blank;
        selectedCopy[2] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }
    if (window.location.pathname === "/account" || window.location.pathname === "/editaccount") {
        let selectedCopy = blank;
        selectedCopy[3] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

    if (window.location.pathname === "/") {
        let selectedCopy = blank;
        selectedCopy[4] = "selected";
        if (selected !== selectedCopy) {
            selected = selectedCopy;
        }
    }

console.log(auth.fullUser);


    return (
        <div id="navbar" >
            <Link to="/" className={"btn " + selected[4]} id="home">Home</Link>
            <Link to="/workoutcatalog" className={"btn " + selected[0]} id="workoutCatalog">Workout Catalog</Link>
            <Link to="workouthistory" className={"btn " + selected[1]} id="workoutHistory" >Workout History</Link>
            <Link to="account" className={"btn " + selected[3]} id="account" >Account</Link>

            {auth.user !== null &&
                <button type="button" className={"btn"} id="logout" onClick={auth.logout}>Logout</button>
            }
            {auth.user !== null &&
                <span>Hello,&nbsp;{auth.fullUser.firstName}</span>
            }
        </div>
    )
}

export default UserNavBar;