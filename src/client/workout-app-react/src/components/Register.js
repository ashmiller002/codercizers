import { useState, useContext } from 'react';
import { useHistory, Link } from 'react-router-dom';
import LoginContext from '../contexts/LoginContext';
import { register } from '../services/auth';
import Error from './Error';


function Register() {

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
    
        register(credentials)
        .then((body) => {
        const { id } = body;
        console.log(id);

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
                <form className="login" onSubmit={handleSubmit}>
                    <div className="input-field">
                        <input placeholder="Username" type="text" id="username" name="username" class="validate" value={credentials.username} onChange={onChange} />
                        <span class="helper-text">We'll never share your email with anyone else.</span>
                    </div>
                    <div className="input-field">
                        <input placeholder="Password" type="password" id="password" name="password" value={credentials.password} onChange={onChange} />
                    </div>
                    <div>
                        <button type="submit" className="btn">Register</button>
                        <span class="new">Account already exists? <Link to="/login" class="registerLink">Login Here</Link></span>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Register;