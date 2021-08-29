import { useState, useContext } from 'react';
import { useHistory, Link } from 'react-router-dom';
import FullUserContext from '../contexts/FullUserContext';
import LoginContext from '../contexts/LoginContext';
import { register } from '../services/auth';
import { editUser } from '../services/user';
import Error from './Error';
import './Register.css';


function EditAccount() {

    const [errors, setErrors] = useState();
    const auth = useContext(LoginContext);
    const history = useHistory();
    console.log("hello");
    console.log(auth.fullUser);
    const blankCredentials = {
        username: "",
        password: ""
    }

    const blankFullUserInfo = {
        loginId: "",
        firstName: "",
        lastName: "",
        dateBirth: "",
        email: "",
        goal: "",
        activityLevel: "",
    }

    const [fullUserInfo, setFullUserInfo] = useState(auth.fullUser);

    function onChangeFullUser(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        nextFullUserInfo[evt.target.name] = evt.target.value;
        setFullUserInfo(nextFullUserInfo);
    }

    function onChangeActivityLevel(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        if (evt.target.checked) {
            nextFullUserInfo.activityLevel = evt.target.value;
            setFullUserInfo(nextFullUserInfo);
        }
    }

    function onChangeGoal(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        if (evt.target.checked) {
            nextFullUserInfo.goal = evt.target.value;
            setFullUserInfo(nextFullUserInfo);
        }
    }


    function handleSubmit(evt) {
        
        evt.preventDefault();
        handleEdit({...fullUserInfo});

    }

    function handleEdit(user) {
        editUser(user)
            .then(() => {
                history.push("/account")})
            .catch(error => {
                setErrors(error);
            });

    }

    return (
        <div className="login">
            <div className="container ">
                <h2>Edit Account</h2>
                <Error errorMessages={errors} />
                <form onSubmit={handleSubmit} className="register">

                    <div className="input-field">
                        <input placeholder="First Name" required type="text" id="firstName" name="firstName" value={fullUserInfo.firstName} onChange={onChangeFullUser} />
                        <label class="active" htmlFor="firstName">First Name</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Last Name" required type="text" id="lastName" name="lastName" value={fullUserInfo.lastName} onChange={onChangeFullUser} />
                        <label class="active" htmlFor="lastName">Last Name</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Date of Birth" required type="date" id="dateBirth" name="dateBirth" value={fullUserInfo.dateBirth} onChange={onChangeFullUser} />
                        <label class="active" htmlFor="dateBirth">Date of Birth</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Email" type="email" required id="email" name="email" value={fullUserInfo.email} onChange={onChangeFullUser} />
                        <label class="active" htmlFor="email">Email</label>
                    </div>
                    <div className="activityLevelOrGoal">

                        <label><div className="radioPrompt">How often do you exercise?</div>
                            <input className="with-gap" name="activityLevel" value="1" type="radio" required checked={fullUserInfo.activityLevel === "1"} onChange={onChangeActivityLevel} />
                            <span>Less than 3 times per week</span>
                        </label><br />
                        <label>
                            <input className="with-gap" name="activityLevel" value="2" type="radio" checked={fullUserInfo.activityLevel === "2"} onChange={onChangeActivityLevel} />
                            <span>3 times per week or more</span>
                        </label>
                    </div>
                    <div className="activityLevelOrGoal">
                        <label><div className="radioPrompt">What is your goal?</div>
                            <input className="with-gap" name="goal" value="1" type="radio" required checked={fullUserInfo.goal === "1"} onChange={onChangeGoal} />
                            <span>Strength</span>
                        </label><br />
                        <label>
                            <input className="with-gap" name="goal" value="2" type="radio" checked={fullUserInfo.goal === "2"} onChange={onChangeGoal} />
                            <span>Mobility</span>
                        </label><br />
                        <label>
                            <input className="with-gap" name="goal" value="3" type="radio" checked={fullUserInfo.goal === "3"} onChange={onChangeGoal} />
                            <span>Lose weight</span>
                        </label>
                    </div>
                    <div>
                        <button type="submit" className="btn">Edit</button>
                        <Link to="/account" type="button" className="btn cancel">Cancel</Link>
                    </div>


                </form>
            </div>
        </div>
    )
}

export default EditAccount;