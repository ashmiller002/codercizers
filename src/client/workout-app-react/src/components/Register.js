import { useState, useContext } from 'react';
import { useHistory, Link } from 'react-router-dom';
import FullUserContext from '../contexts/FullUserContext';
import LoginContext from '../contexts/LoginContext';
import { register } from '../services/auth';
import { registerUser } from '../services/user';
import Error from './Error';
import './Register.css';


function Register() {

    const [errors, setErrors] = useState();
    const auth = useContext(LoginContext);
    const fullUser = useContext(FullUserContext);
    const history = useHistory();


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
        goalId: "",
        activityLevelId: "",
        programId: 0
    }

    const [credentials, setCredentials] = useState(blankCredentials);
    const [fullUserInfo, setFullUserInfo] = useState(blankFullUserInfo);

    const onChangeLogin = (evt) => {
        const nextCredentials = { ...credentials };
        nextCredentials[evt.target.name] = evt.target.value;
        setCredentials(nextCredentials);
    };

    function onChangeFullUser(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        nextFullUserInfo[evt.target.name] = evt.target.value;
        setFullUserInfo(nextFullUserInfo);
    }

    function onChangeActivityLevel(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        if (evt.target.checked) {
            nextFullUserInfo.activityLevelId = evt.target.value;
            setFullUserInfo(nextFullUserInfo);
        }
    }

    function onChangeGoal(evt) {
        const nextFullUserInfo = { ...fullUserInfo };
        if (evt.target.checked) {
            nextFullUserInfo.goalId = evt.target.value;
            setFullUserInfo(nextFullUserInfo);
        }
    }


   function handleSubmit(evt) {
        evt.preventDefault();
        register(credentials)
            .then((body) => {
                const { id } = body;
                const nextFullUserInfo = { ...fullUserInfo };
                nextFullUserInfo.loginId = id;
                setErrors();
                handleRegistration(nextFullUserInfo);
            })
            .catch(errors => {
                setErrors(errors.messages);
            });
    }

    function handleRegistration(user) {
        registerUser(user)
        .then(history.push("/login"))
        .catch( errors => {
            setErrors(errors.messages);
        });

    }

    return (
        <div className="login">
            <div className="container ">
                <h1>Welcome to Workout Buddy!</h1>
                <h4>Register</h4>
                <Error errorMessages={errors} />
                <form onSubmit={handleSubmit} className="register">
                    <div className="input-field">
                        <input placeholder="Username" required type="text" id="username" name="username" className="validate" value={credentials.username} onChange={onChangeLogin} />
                        <label class="active" htmlFor="username">Username</label>
                        <span class="helper-text">We'll never share your email with anyone else.</span>
                    </div>
                    <div className="input-field">
                        <input placeholder="Password" required type="password" id="password" name="password" value={credentials.password} onChange={onChangeLogin} />
                        <label class="active" htmlFor="password">Password</label>
                    </div>
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
                            <input className="with-gap" name="activityLevel" value="1" type="radio" required checked={fullUserInfo.activityLevel === "1"} onChange={onChangeActivityLevel}/>
                            <span>Less than 3 times per week</span>
                        </label><br />
                        <label>
                            <input className="with-gap" name="activityLevel" value="2" type="radio" checked={fullUserInfo.activityLevel === "2"} onChange={onChangeActivityLevel}/>
                            <span>3 times per week or more</span>
                        </label>
                    </div>
                    <div className="activityLevelOrGoal">
                        <label><div className="radioPrompt">What is your goal?</div>
                            <input className="with-gap" name="goal" value="1" type="radio" required checked={fullUserInfo.goal === "1"} onChange={onChangeGoal}/>
                            <span>Strength</span>
                        </label><br />
                        <label>
                            <input className="with-gap" name="goal" value="2" type="radio" checked={fullUserInfo.goal === "2"} onChange={onChangeGoal}/>
                            <span>Mobility</span>
                        </label><br/>
                        <label>
                            <input className="with-gap" name="goal" value="3" type="radio" checked={fullUserInfo.goal === "3"} onChange={onChangeGoal}/>
                            <span>Lose weight</span>
                        </label>
                    </div>
                    <div>
                        <button type="submit" className="btn">Register</button>
                        <span className="new">Account already exists? <Link to="/login" className="registerLink">Login Here</Link></span>
                    </div>


                </form>
            </div>
        </div>
    )
}

export default Register;