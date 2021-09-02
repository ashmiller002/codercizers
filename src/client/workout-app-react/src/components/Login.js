import jwtDecode from 'jwt-decode';
import { useState, useContext } from 'react';
import { useHistory, Link } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../services/auth.js';
import { getUserWithLoginId } from '../services/user';
import Error from './Error';
import './Login.css';



function Login() {

    const [errors, setErrors] = useState();
    const auth = useContext(LoginContext);
    const history = useHistory();
    const blankCredentials = {
        username: "",
        password: ""
    }

    const onChange = (evt) => {
        const nextCredentials = { ...credentials };
        nextCredentials[evt.target.name] = evt.target.value;
        setCredentials(nextCredentials);
    };

    const [credentials, setCredentials] = useState(blankCredentials);

    const handleSubmit = (evt) => {
        evt.preventDefault();

        authenticate(credentials)
            .then(body => {
                if (body === null) {
                    setErrors(["Username/Password combination does not exist."])
                } else {
                    const { jwt_token } = body;
                    localStorage.setItem('jwt_token', jwt_token);
                    const { id, roles } = jwtDecode(jwt_token);
                    if (roles === "USER") {
                        setUserInformation(id, () => {
                            auth.onAuthenticated(jwt_token);
                            history.push("/");
                        });

                    } else {
                        auth.onAuthenticated(jwt_token);
                        history.push("/");
                    }
                }
            })
            .catch(err => {
                console.error(err);
            })
    }

    function setUserInformation(id, onSuccess) {
        getUserWithLoginId(id)
            .then((userInfo) => {
                auth.setFullUserInformation(userInfo);
                onSuccess();
            }
            )
            .catch((err) => {
                setErrors(err);
                auth.logout();
            }
            )
    }
    return (
        <div className="login">
            <div className="container ">
                <h1 className="workoutBuddy">Welcome to Workout Buddy!</h1>
                <h4>Login</h4>
                <Error errorMessages={errors} />
                <form className="login" onSubmit={handleSubmit}>
                    <div className="input-field">
                        <input placeholder="Username" type="text" id="username" name="username" className="validate" value={credentials.username} onChange={onChange} />
                    </div>
                    <div className="input-field">
                        <input placeholder="Password" type="password" id="password" name="password" value={credentials.password} onChange={onChange} />
                    </div>
                    <div>
                        <button type="submit" className="btn">Login</button>
                        <span className="new">New? <Link to="/register" className="registerLink">Register Here</Link></span>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;