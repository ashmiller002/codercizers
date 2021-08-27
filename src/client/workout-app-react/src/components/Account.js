import LoginContext from "../contexts/LoginContext";
import FullUserContext from "../contexts/FullUserContext"
import { useContext } from "react";
import { Link } from "react-router-dom";

function Account() {

    const auth = useContext(LoginContext);
    function getGoal() {
        switch (auth.fullUser.goal) {
            case "1": return "Strength";
            case "2": return "Mobility";
            case "3": return "Weight Loss";
            default: return "Unknown";
        }
    }

    const realGoal = getGoal();

    function getActivityLevel() {
        switch (auth.fullUser.activityLevel) {
            case "1": return "Less than 3 times per week";
            case "2": return "3 times per week or more";
        }
    }

    const realActivityLevel = getActivityLevel();



    return (
        <div className="container">
            <h2>Account</h2>
            {/* Add username  */}
            <p><b>First Name:</b> {auth.fullUser.firstName}</p>
            <p><b>Last Name:</b> {auth.fullUser.lastName}</p>
            <p><b>Date of Birth:</b> {auth.fullUser.dateBirth}</p>
            <p><b>Email:</b> {auth.fullUser.email}</p>
            <p><b>Goal:</b> {realGoal}</p>
            <p><b>How Often Do You Exercise?</b> {realActivityLevel}</p>
            <Link to="/editaccount" className="btn">Edit</Link>
        </div>
    )
}

export default Account;
