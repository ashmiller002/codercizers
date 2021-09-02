import React, { useContext } from 'react';
import { View,  Text, Button } from "react-native";
import LoginContext from '../contexts/LoginContext';

function Home({history}) {

    auth = useContext(LoginContext);
    return(
        <View>
            {auth.user !== null && <Text>{auth.user[0]}</Text>}
            <Button title="Login" onPress={() => history.push("/login")} />
            <Button title="Get Suggested Workout" onPress={() => history.push("/getsuggestedworkout")} />
        </View>

    )
}

export default Home;