import { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../services/auth.js';
import Error from './Error';


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
        <div className="container">
            <h1>Login</h1>
            <Error errorMessages={errors}/>
            <form className="login" onSubmit={handleSubmit}>
                <div>
                    <label>Username: </label>
                    <input type="text" id="username" name="username" value={credentials.username} onChange={onChange} />
                </div>
                <div>
                    <label>Password: </label>
                    <input type="password" id="password" name="password" value={credentials.password} onChange={onChange} />
                </div>
                <div>
                    <button type="submit" className="btn">Login</button>
                </div>
            </form>
        </div>
    )
}

export default Login;