import { useState, useContext, useEffect } from 'react';
import { useHistory, Link } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../services/auth.js';
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

    const [credentials, setCredentials] = useState(blankCredentials)

    const handleSubmit = (evt) => {
        evt.preventDefault();

        authenticate(credentials)
            .then(body => {
                if (body === null) {
                    setErrors(["Username/Password combination does not exist."])
                } else {
                    const { jwt_token } = body;
                    auth.onAuthenticated(jwt_token);
                    
                    history.push("/");
                }
            })
            .catch(err => {
                console.error(err);
            })
    }

    return (
        <div className="login">
            <div className="container ">
                <h1>Welcome to Workout Buddy!</h1>
                <h4>Login</h4>
                <Error errorMessages={errors} />
                <form className="login" onSubmit={handleSubmit}>
                    <div className="input-field">
                        <input placeholder="Username" type="text" id="username" name="username" class="validate" value={credentials.username} onChange={onChange} />
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