import { useState, useContext } from 'react';
import { useHistory, Link } from 'react-router-dom';
import FullUserContext from '../contexts/FullUserContext';
import LoginContext from '../contexts/LoginContext';
import { register } from '../services/auth';
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
        firstName: "",
        lastName: "",
        dateBirth: "",
        email: "",
        goal: "",
        activityLevel: "",
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



    const handleSubmit = (evt) => {
        evt.preventDefault();

        register(credentials)
            .then((body) => {
                const { id } = body;
                fullUser.loginId = id;
                console.log(fullUser.loginId)

            })
            .catch(errors => {
                setErrors(errors.messages);
            });
    }

    return (
        <div className="login">
            <div className="container ">
                <h1>Welcome to Workout Buddy!</h1>
                <h4>Register</h4>
                <Error errorMessages={errors} />
                <form className="login" onSubmit={handleSubmit} className="register">
                    <div className="input-field">
                        <input placeholder="Username" type="text" id="username" name="username" class="validate" value={credentials.username} onChange={onChangeLogin} />
                        <label class="active" for="username">Username</label>
                        <span class="helper-text">We'll never share your email with anyone else.</span>
                    </div>
                    <div className="input-field">
                        <input placeholder="Password" type="password" id="password" name="password" value={credentials.password} onChange={onChangeLogin} />
                        <label class="active" for="password">Password</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="First Name" type="text" id="firstName" name="firstName" value={fullUserInfo.firstName} onChange={onChangeFullUser} />
                        <label class="active" for="firstName">First Name</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Last Name" type="text" id="lastName" name="lastName" value={fullUserInfo.lastName} onChange={onChangeFullUser} />
                        <label class="active" for="lastName">Last Name</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Date of Birth" type="date" id="dateBirth" name="dateBirth" value={fullUserInfo.dateBirth} onChange={onChangeFullUser} />
                        <label class="active" for="dateBirth">Date of Birth</label>
                    </div>
                    <div className="input-field">
                        <input placeholder="Email" type="email" id="email" name="email" value={fullUserInfo.email} onChange={onChangeFullUser} />
                        <label class="active" for="email">Email</label>
                    </div>
                    <div>

                        <label><div className="radioPrompt">How often do you exercise?</div>
                            <input class="with-gap" name="group1" type="radio" />
                            <span>Less than 3 times per week</span>
                        
                            <input class="with-gap" name="group1" type="radio" />
                            <span>3 times per week or more</span>
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