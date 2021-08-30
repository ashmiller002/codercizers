import LoginContext from "../contexts/LoginContext";
import { useContext, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { getUserWithLoginId } from "../services/user.js";

function Account() {

    const blankFullUserInfo = {
        loginId: "",
        firstName: "",
        lastName: "",
        dateBirth: "",
        email: "",
        goal: "",
        activityLevel: "",
    }

    const auth = useContext(LoginContext);
    const [fullUserInfo, setFullUserInfo] = useState(blankFullUserInfo);
   
    useEffect(() => {
        getUserWithLoginId(auth.user[2])
        .then((data) => {
            setFullUserInfo(data);
        })
        .catch(errs => {
            //setErrors(errs);
        })

    }, [])

    function getGoal() {
        switch (fullUserInfo.goalId) {
            case 1: return "Strength";
            case 2: return "Mobility";
            case 3: return "Weight Loss";
            default: return "Unknown";
        }
    }

    const realGoal = getGoal();

    function getActivityLevel() {
        switch (fullUserInfo.activityLevelId) {
            case 1: return "Less than 3 times per week";
            case 2: return "3 times per week or more";
        }
    }

    const realActivityLevel = getActivityLevel();


    return (
        <div className="container">
            <h2>Account</h2>
            {/* Add username  */}
            <p><b>First Name:</b> {fullUserInfo.firstName}</p>
            <p><b>Last Name:</b> {fullUserInfo.lastName}</p>
            <p><b>Date of Birth:</b> {fullUserInfo.dateBirth}</p>
            <p><b>Email:</b> {fullUserInfo.email}</p>
            <p><b>Goal:</b> {realGoal}</p>
            <p><b>How Often Do You Exercise?</b> {realActivityLevel}</p>
            <Link to={`/editaccount/${fullUserInfo.loginId}`} className="btn">Edit</Link>
        </div>
    )
}

export default Account;
