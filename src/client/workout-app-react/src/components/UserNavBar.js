import { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import "./UserNavBar.css";
import LoginContext from "../contexts/LoginContext";
import M from 'materialize-css/dist/js/materialize.min.js';

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
    useEffect(() => {
        let sidenav = document.querySelector('#mobile-demo');
        M.Sidenav.init(sidenav, {});
    }, [])



    return (
        <div>
            <nav id="navbar" >
                <div className="nav-wrapper">
                    <a href={window.location.href} className="sidenav-trigger" data-target="mobile-demo"><i className="material-icons">menu</i></a>
                    <ul className="hide-on-med-and-down">
                        <li><Link to="/" className={"btn " + selected[4]} id="home">Home</Link></li>
                        <li><Link to="/workoutcatalog" className={"btn " + selected[0]} id="workoutCatalog">Workout Catalog</Link></li>
                        <li><Link to="/workouthistory" className={"btn " + selected[1]} id="workoutHistory" >Workout History</Link></li>
                        <li><Link to="/account" className={"btn " + selected[3]} id="account" >Account</Link></li>

                        {auth.user !== null &&
                            <li><button type="button" className="btn" id="logout" onClick={auth.logout}>Logout</button></li>
                        }
                        {auth.user !== null &&
                            <li><span>Hello,&nbsp;{auth.fullUser.firstName}</span></li>
                        }
                    </ul>
                </div>
            </nav>
            <ul className="sidenav" id="mobile-demo">
                <li><Link to="/" className={"btn " + selected[4]} id="home">Home</Link></li>
                <li><Link to="/workoutcatalog" className={"btn " + selected[0]} id="workoutCatalog">Workout Catalog</Link></li>
                <li><Link to="/workouthistory" className={"btn " + selected[1]} id="workoutHistory" >Workout History</Link></li>
                <li><Link to="/account" className={"btn " + selected[3]} id="account" >Account</Link></li>

                {auth.user !== null &&
                    <li><a href={window.location.href} className="btn" id="logout" onClick={auth.logout}>Logout</a></li>
                }
            </ul>

        </div>
    )
}

export default UserNavBar;