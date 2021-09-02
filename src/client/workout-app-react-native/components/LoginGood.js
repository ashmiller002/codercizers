// import jwtDecode from 'jwt-decode';
// import { useState, useContext } from 'react';
// import { useHistory, Link } from 'react-router-native';
// import LoginContext from '../contexts/LoginContext';
// import { authenticate } from '../services/auth.js';
// import { getUserWithLoginId } from '../services/user';
// import Error from './Error';
// import './Login.css';
import React from 'react';
import { StyleSheet, Text, View, SafeAreaView, Button } from 'react-native';



// function LoginGood() {

//     const [errors, setErrors] = useState();
//     const auth = useContext(LoginContext);
//     const history = useHistory();
//     const blankCredentials = {
//         username: "",
//         password: ""
//     }

//     const onChange = (evt) => {
//         const nextCredentials = { ...credentials };
//         nextCredentials[evt.target.name] = evt.target.value;
//         setCredentials(nextCredentials);
//     };

//     const [credentials, setCredentials] = useState(blankCredentials);

//     const handleSubmit = (evt) => {
//         evt.preventDefault();

//         authenticate(credentials)
//             .then(body => {
//                 if (body === null) {
//                     setErrors(["Username/Password combination does not exist."])
//                 } else {
//                     const { jwt_token } = body;
//                     localStorage.setItem('jwt_token', jwt_token);
//                     const { id, roles } = jwtDecode(jwt_token);
//                     if (roles === "USER") {
//                         setUserInformation(id, () => {
//                             auth.onAuthenticated(jwt_token);
//                             history.push("/");
//                         });

//                     } else {
//                         auth.onAuthenticated(jwt_token);
//                         history.push("/");
//                     }
//                 }
//             })
//             .catch(err => {
//                 console.error(err);
//             })
//     }

//     function setUserInformation(id, onSuccess) {
//         getUserWithLoginId(id)
//             .then((userInfo) => {
//                 auth.setFullUserInformation(userInfo);
//                 onSuccess();
//             }
//             )
//             .catch((err) => {
//                 setErrors(err);
//                 auth.logout();


//             }
//             )
//     }
//     return (
//         <View className="login">
//             <View className="container ">
//                 <Text>Welcome to Workout Buddy!</Text>
//                 <Text>Login</Text>
//                 {/* <Error errorMessages={errors} /> */}

//                     <View className="input-field">
//                         <TextInput placeholder="Username" type="text" id="username" name="username" className="validate" value={credentials.username} onChange={onChange} />
//                     </View>
//                     <View className="input-field">
//                         <TextInput placeholder="Password" type="password" id="password" name="password" value={credentials.password} onChange={onChange} />
//                     </View>
//                     <View>
//                         <Button type="submit" className="btn">Login</Button>
//                         <Text className="new">New? </Text><Link to="/register" className="registerLink">Register Here</Link>
//                     </View>
//             </View>
//         </View>
//     )
// }

// export default LoginGood;

function LoginGood({history}) {
    return (
        <View>
            <Text>Login page here!</Text>
            <Button title="Home" onPress={() => history.push("/")} />
        </View>
    )
}

export default LoginGood;