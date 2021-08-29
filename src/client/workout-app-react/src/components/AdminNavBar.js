import { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./UserNavBar.css";
import LoginContext from "../contexts/LoginContext";
import M from 'materialize-css/dist/js/materialize.min.js';

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

    useEffect(() => {
        let sidenav = document.querySelector('#mobile-demo');
        M.Sidenav.init(sidenav, {});
    }, [])

    return (
        <div>
            <nav id="navbar" >
                <div className="nav-wrapper">
                    <Link className="sidenav-trigger" data-target="mobile-demo"><i className="material-icons">menu</i></Link>
                    <ul className="hide-on-med-and-down">
                        <li><Link to="/" className={"btn " + selected[0]} id="home" onClick={handleClick}>Home</Link></li>
                        <li><Link to="/adminworkoutcatalog" className={"btn " + selected[1]} id="adminWorkoutCatalog" onClick={handleClick}>Workout Catalog</Link></li>
                        <li><Link to="/addworkout" className={"btn " + selected[2]} id="addWorkout" onClick={handleClick}>Add Workout</Link></li>
                        {auth.user !== null &&
                            <li> <button type="button" className={"btn"} id="logout" onClick={auth.logout}>Logout</button></li>
                        }
                        {auth.user !== null &&
                            <li><span>Hello,&nbsp;{auth.user[0]}</span></li>
                        }
                    </ul>
                </div>
            </nav>
            <ul className="sidenav" id="mobile-demo">
                <li><Link to="/" className={"btn " + selected[0]} id="home" onClick={handleClick}>Home</Link></li>
                <li><Link to="/adminworkoutcatalog" className={"btn " + selected[1]} id="adminWorkoutCatalog" onClick={handleClick}>Workout Catalog</Link></li>
                <li><Link to="/addworkout" className={"btn " + selected[2]} id="addWorkout" onClick={handleClick}>Add Workout</Link></li>
                {auth.user !== null &&
                    <li> <Link className={"btn"} id="logout" onClick={auth.logout}>Logout</Link></li>
                }
            </ul>
        </div>
    )
}

export default AdminNavBar;