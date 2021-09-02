// import jwtDecode from 'jwt-decode';
// import { useState, useContext } from 'react';
// import { useHistory, Link } from 'react-router-native';
// import LoginContext from '../contexts/LoginContext';
// import { authenticate } from '../services/auth.js';
// import { getUserWithLoginId } from '../services/user';
// import Error from './Error';
// import './Login.css';
import React from 'react';
import { useState, useContext } from 'react';
import { StyleSheet, Text, View, SafeAreaView, Button, TextInput } from 'react-native';
import Error from './Error';
import LoginContext from '../contexts/LoginContext';
import { authenticate } from '../services/auth.js';
import AsyncStorage from '@react-native-async-storage/async-storage';
import jwtDecode from 'jwt-decode';



function LoginGood({ history }) {

    const [errors, setErrors] = useState();
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const auth = useContext(LoginContext);
    const blankCredentials = {
        username: "",
        password: ""
    }


    const [credentials, setCredentials] = useState(blankCredentials);

    const handleSubmit = () => {
        setCredentials({
            username: username,
            password: password
        })

        authenticate(credentials)
            .then(async (body) => {
                if (body === null) {
                    setErrors(["Username/Password combination does not exist."])
                } else {
                    const { jwt_token } = body;
                    await AsyncStorage.setItem('jwt_token', jwt_token);
                    const { id, roles } = jwtDecode(jwt_token);
                    // if (roles === "USER") {
                    //     setUserInformation(id, () => {
                    //         auth.onAuthenticated(jwt_token);
                    //         history.push("/");
                    //     });

                    // } else {
                    //     auth.onAuthenticated(jwt_token);
                    //     history.push("/");
                    // }
                }
            })
            .catch(err => {
                console.error(err);
            })
    }

    // function setUserInformation(id, onSuccess) {
    //     getUserWithLoginId(id)
    //         .then((userInfo) => {
    //             auth.setFullUserInformation(userInfo);
    //             onSuccess();
    //         }
    //         )
    //         .catch((err) => {
    //             setErrors(err);
    //             auth.logout();


    //         }
    //         )
    // }
    return (
        <View>
            <View>
                <Text>Welcome to Workout Buddy!</Text>
                <Text>Login</Text>
                <Error errorMessages={errors} />

                <View>
                    <TextInput placeholder="Username" type="text" id="username" name="username" className="validate" value={username} onChangeText={(newUsername) => { setUsername(newUsername) }} />
                </View>
                <View>
                    <TextInput placeholder="Password" type="password" id="password" name="password" value={password} onChangeText={(newPassword) => { setPassword(newPassword) }} />
                </View>
                <View>
                    <Button type="submit" title="Login" onPress={handleSubmit} />

                    <Button title="Home" onPress={() => history.push("/")} />
                </View>
            </View>
        </View>
    )
}


// function LoginGood({history}) {
// return (
//     <View>
//         <Text>Login page here!</Text>
//         <Error errorMessages={["This is an error"]} />
//         <Button title="Home" onPress={() => history.push("/")} />
//     </View>
// )


export default LoginGood;